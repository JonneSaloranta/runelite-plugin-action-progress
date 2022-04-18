package com.github.calebwhiting.runelite.data;

import com.github.calebwhiting.runelite.api.data.IDs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import net.runelite.api.ItemID;

@UtilityClass
public class Cooking {
    @Getter
    @RequiredArgsConstructor
    public enum Cookable {
        PLAIN_PIZZA(ItemID.UNCOOKED_PIZZA, ItemID.PLAIN_PIZZA),
        SARDINE(ItemID.RAW_SARDINE, ItemID.SARDINE),
        SALMON(ItemID.RAW_SALMON, ItemID.SALMON),
        TROUT(ItemID.RAW_TROUT, ItemID.TROUT),
        COD(ItemID.RAW_COD, ItemID.COD),
        HERRING(ItemID.RAW_HERRING, ItemID.HERRING),
        PIKE(ItemID.RAW_PIKE, ItemID.PIKE),
        MACKEREL(ItemID.RAW_MACKEREL, ItemID.MACKEREL),
        TUNA(new IDs(ItemID.RAW_TUNA), new IDs(ItemID.TUNA, ItemID.TUNA_26149)),
        BASS(ItemID.RAW_BASS, ItemID.BASS),
        SWORDFISH(ItemID.RAW_SWORDFISH, ItemID.SWORDFISH),
        LOBSTER(ItemID.RAW_LOBSTER, ItemID.LOBSTER),
        SHARK(new IDs(ItemID.RAW_SHARK), new IDs(ItemID.SHARK, ItemID.SHARK_6969, ItemID.SHARK_20390)),
        LAVA_EEL(ItemID.RAW_LAVA_EEL, ItemID.LAVA_EEL),
        MANTA_RAY(ItemID.RAW_MANTA_RAY, ItemID.MANTA_RAY),
        MONKFISH(new IDs(ItemID.RAW_MONKFISH), new IDs(ItemID.MONKFISH, ItemID.MONKFISH_20547)),
        DARK_CRAB(ItemID.RAW_DARK_CRAB, ItemID.DARK_CRAB),
        ANGLERFISH(ItemID.RAW_ANGLERFISH, ItemID.ANGLERFISH),
        KARAMBWAN(new IDs(ItemID.RAW_KARAMBWAN), new IDs(ItemID.COOKED_KARAMBWAN, ItemID.COOKED_KARAMBWAN_3147, ItemID.COOKED_KARAMBWAN_23533, ItemID.POISON_KARAMBWAN)),
        SLIMY_EEL(ItemID.RAW_SLIMY_EEL, ItemID.COOKED_SLIMY_EEL),
        RAINBOW_FISH(ItemID.RAW_RAINBOW_FISH, ItemID.RAINBOW_FISH),
        HARPOONFISH(ItemID.RAW_HARPOONFISH, ItemID.HARPOONFISH),
        APPLE_PIE(ItemID.UNCOOKED_APPLE_PIE, ItemID.APPLE_PIE),
        REDBERRY_PIE(ItemID.UNCOOKED_BERRY_PIE, ItemID.REDBERRY_PIE),
        MEAT_PIE(ItemID.UNCOOKED_MEAT_PIE, ItemID.MEAT_PIE),
        BOTANICAL_PIE(ItemID.UNCOOKED_BOTANICAL_PIE, ItemID.BOTANICAL_PIE),
        MUSHROOM_PIE(ItemID.UNCOOKED_MUSHROOM_PIE, ItemID.MUSHROOM_PIE),
        DRAGONFRUIT_PIE(ItemID.UNCOOKED_DRAGONFRUIT_PIE, ItemID.DRAGONFRUIT_PIE),
        ADMIRAL_PIE(ItemID.RAW_ADMIRAL_PIE, ItemID.ADMIRAL_PIE),
        FISH_PIE(ItemID.RAW_FISH_PIE, ItemID.FISH_PIE),
        GARDEN_PIE(ItemID.RAW_GARDEN_PIE, ItemID.GARDEN_PIE),
        SUMMER_PIE(ItemID.RAW_SUMMER_PIE, ItemID.SUMMER_PIE),
        WILD_PIE(ItemID.RAW_WILD_PIE, ItemID.WILD_PIE),
        UGTHANKI_MEAT(ItemID.RAW_UGTHANKI_MEAT, ItemID.UGTHANKI_MEAT),
        THIN_SNAIL(ItemID.THIN_SNAIL, ItemID.THIN_SNAIL_MEAT),
        LEAN_SNAIL(ItemID.LEAN_SNAIL, ItemID.LEAN_SNAIL_MEAT),
        FAT_SNAIL(ItemID.FAT_SNAIL, ItemID.FAT_SNAIL_MEAT),
        MYSTERY_MEAT(ItemID.RAW_MYSTERY_MEAT, ItemID.COOKED_MYSTERY_MEAT),
        CHICKEN(new IDs(ItemID.RAW_CHICKEN, ItemID.RAW_CHICKEN_4289), new IDs(ItemID.COOKED_CHICKEN, ItemID.COOKED_CHICKEN_4291)),
        MEAT(new IDs(ItemID.RAW_BEEF, ItemID.RAW_BEEF_4287, ItemID.RAW_RAT_MEAT, ItemID.RAW_BEAR_MEAT, ItemID.RAW_YAK_MEAT), new IDs(ItemID.COOKED_MEAT)),
        WRAPPED_OOMLIE(ItemID.WRAPPED_OOMLIE, ItemID.COOKED_OOMLIE_WRAP),
        CHOMPY(ItemID.RAW_CHOMPY, ItemID.COOKED_CHOMPY),
        SKEWERED_CHOMPY(ItemID.SKEWERED_CHOMPY, ItemID.COOKED_CHOMPY_7228),
        CRAB_MEAT(ItemID.CRAB_MEAT, ItemID.COOKED_CRAB_MEAT),
        JUBBLY(ItemID.RAW_JUBBLY, ItemID.COOKED_JUBBLY),
        SKEWERED_BIRD_MEAT(ItemID.SKEWERED_BIRD_MEAT, ItemID.ROAST_BIRD_MEAT),
        SKEWERED_BEAST(new IDs(ItemID.RAW_BEAST_MEAT, ItemID.SKEWERED_BEAST), new IDs(ItemID.ROAST_BEAST_MEAT)),
        RABBIT(ItemID.RAW_RABBIT, ItemID.COOKED_RABBIT),
        SKEWERED_RABBIT(ItemID.SKEWERED_RABBIT, ItemID.ROAST_RABBIT),
        SPIDER_ON_STICK(ItemID.SPIDER_ON_STICK, ItemID.SPIDER_ON_STICK_6297),
        BAKED_POTATO(ItemID.POTATO, ItemID.BAKED_POTATO),
        SWEETCORN(ItemID.SWEETCORN, ItemID.COOKED_SWEETCORN),
        GIANT_SEAWEED(ItemID.GIANT_SEAWEED, ItemID.SODA_ASH),
        SEAWEED(ItemID.SEAWEED, ItemID.SODA_ASH),
        EDIBLE_SEAWEED(ItemID.EDIBLE_SEAWEED, ItemID.SODA_ASH);
        private final IDs raw;
        private final IDs cooked;

        Cookable(int raw, int cooked) {
            this(new IDs(raw), new IDs(cooked));
        }
    }
}