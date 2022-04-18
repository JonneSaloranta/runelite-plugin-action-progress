package com.github.calebwhiting.runelite.api;

import com.github.calebwhiting.runelite.api.event.InterruptEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;

import java.util.Arrays;

@Slf4j
@Singleton
public class InterruptionListener {
    private static final int[] WIDGET_CLICK_INTERRUPTS = {
            /*
             * Tab buttons
             */
            7602203, 7602204, 7602206, /* settings buttons */
            25362433, 25362435, 25362437, 25362439, /* equipment buttons */
            38862852, 38862856, 38862860, 38862864, 38862878,/* combat buttons */
            /*
             * Equipment slots
             */
            25362447, 25362448, 25362449, 25362457, 25362450, 25362451, 25362452, 25362453, 25362455, 25362454,
            25362456,
            /*
             * Prayers
             */
            35454981, 35454982, 35454983, 35454984, 35454985, 35454986, 35454987, 35454988, 35454989, 35454990,
            35454991, 35454992, 35454993, 35454994, 35454995, 35454996, 35454997, 35454998, 35454999, 35455000,
            35455001, 35455002, 35455003, 35455004, 35455005, 35455006, 35455007, 35455008
    };
    private static final int KOUREND_FAVOUR_OVERVIEW_GROUP_ID = 626;
    private static final int CHATBOX_MAKE_GROUP_ID = 270;
    private static final int SKILL_GUIDE_GROUP_ID = 214;
    private static final int COMBAT_ACHIEVEMENTS_GROUP_ID = 717;
    private static final int[] WIDGET_GROUPS_INTERRUPT = {
            WidgetID.COLLECTION_LOG_ID, WidgetID.LEVEL_UP_GROUP_ID, WidgetID.BANK_GROUP_ID, WidgetID.BANK_PIN_GROUP_ID,
            WidgetID.DEPOSIT_BOX_GROUP_ID, WidgetID.ACHIEVEMENT_DIARY_GROUP_ID, WidgetID.ADVENTURE_LOG_ID,
            WidgetID.BANK_INVENTORY_GROUP_ID, WidgetID.DIALOG_NPC_GROUP_ID, WidgetID.DIALOG_PLAYER_GROUP_ID,
            WidgetID.DIALOG_OPTION_GROUP_ID, WidgetID.DIALOG_SPRITE_GROUP_ID, WidgetID.DESTROY_ITEM_GROUP_ID,
            WidgetID.DUEL_INVENTORY_GROUP_ID, WidgetID.DUEL_INVENTORY_OTHER_GROUP_ID,
            WidgetID.EXPLORERS_RING_ALCH_GROUP_ID, WidgetID.FAIRY_RING_GROUP_ID,
            WidgetID.FISHING_TRAWLER_REWARD_GROUP_ID, WidgetID.BARROWS_REWARD_GROUP_ID,
            WidgetID.GRAND_EXCHANGE_GROUP_ID, WidgetID.KILL_LOGS_GROUP_ID, WidgetID.GUIDE_PRICE_GROUP_ID,
            WidgetID.KEPT_ON_DEATH_GROUP_ID, WidgetID.RUNE_POUCH_GROUP_ID, WidgetID.SHOP_GROUP_ID,
            WidgetID.SEED_VAULT_GROUP_ID, WidgetID.SLAYER_REWARDS_GROUP_ID, WidgetID.SMITHING_GROUP_ID,
            WidgetID.DIARY_QUEST_GROUP_ID, KOUREND_FAVOUR_OVERVIEW_GROUP_ID, CHATBOX_MAKE_GROUP_ID,
            SKILL_GUIDE_GROUP_ID, COMBAT_ACHIEVEMENTS_GROUP_ID
    };
    private static final MenuAction[] MENU_ACTIONS_INTERRUPT = {
            MenuAction.WALK, MenuAction.ITEM_FIFTH_OPTION, MenuAction.ITEM_SECOND_OPTION,
            MenuAction.ITEM_USE_ON_GAME_OBJECT, MenuAction.ITEM_USE_ON_GROUND_ITEM, MenuAction.ITEM_USE_ON_PLAYER,
            MenuAction.ITEM_USE_ON_NPC, MenuAction.SPELL_CAST_ON_GROUND_ITEM, MenuAction.SPELL_CAST_ON_PLAYER,
            MenuAction.SPELL_CAST_ON_NPC, MenuAction.SPELL_CAST_ON_GAME_OBJECT, MenuAction.SPELL_CAST_ON_WIDGET
    };
    @Getter private boolean waiting;
    @Inject private Client client;
    @Inject private EventBus eventBus;
    private LocalPoint initialDestination;

    static {
        Arrays.sort(WIDGET_CLICK_INTERRUPTS);
        Arrays.sort(WIDGET_GROUPS_INTERRUPT);
        Arrays.sort(MENU_ACTIONS_INTERRUPT);
    }

    private void interrupt(String message) {
        if (this.waiting) {
            this.eventBus.post(new InterruptEvent());
            this.waiting = false;
            log.debug("Interrupted by {}", message);
        }
    }

    public void setWaiting(boolean waiting) {
        if (waiting) {
            this.initialDestination = this.client.getLocalDestinationLocation();
        }
        this.waiting = waiting;
    }

    @Subscribe
    public void onGameTick(GameTick evt) {
        Player localPlayer = this.client.getLocalPlayer();
        if (localPlayer == null) {
            this.interrupt("null player");
        }
        LocalPoint dest = this.client.getLocalDestinationLocation();
        if (dest != null) {
            if (this.initialDestination == null || dest.distanceTo(this.initialDestination) != 0) {
                this.interrupt("target destination changed");
            }
        }
    }

    @Subscribe
    public void onInteractingChanged(InteractingChanged evt) {
        if (evt.getSource() == this.client.getLocalPlayer()) {
            this.interrupt("interaction changed");
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged evt) {
        if (evt.getGameState() == GameState.LOGIN_SCREEN) {
            this.interrupt("game state changed");
        }
    }

    @Subscribe
    public void onResizeableChanged(ResizeableChanged evt) {
        log.info("Interrupted by resizable mode change");
        this.interrupt("resizable mode change");
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked evt) {
        if (Arrays.binarySearch(MENU_ACTIONS_INTERRUPT, evt.getMenuAction()) >= 0) {
            this.interrupt(String.format("menu action: %s", evt.getMenuAction()));
        } else if (evt.getMenuAction() == MenuAction.CC_OP &&
                Arrays.binarySearch(WIDGET_CLICK_INTERRUPTS, evt.getParam1()) >= 0) {
            this.interrupt(String.format("menu action: %s, widget=%d", evt.getMenuAction(), evt.getParam1()));
        }
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded evt) {
        int groupId = evt.getGroupId();
        if (Arrays.binarySearch(WIDGET_GROUPS_INTERRUPT, groupId) >= 0) {
            this.interrupt(String.format("widget group: %d", groupId));
        }
    }

    @Subscribe
    public void onChatMessage(ChatMessage evt) {
        if (evt.getType() == ChatMessageType.GAMEMESSAGE) {
            if (evt.getMessage().matches("You need level ([0-9]*) ([A-Za-z]*) to (.*)$")) {
                this.interrupt(String.format("level requirement: %s", evt.getMessage()));
            }
        }
    }

}