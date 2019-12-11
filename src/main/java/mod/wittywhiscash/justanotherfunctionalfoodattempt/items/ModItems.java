package com.wittywhiscash.justanotherfunctionalfoodattempt.items;

import com.wittywhiscash.justanotherfunctionalfoodattempt.JustAnotherFunctionalFoodAttempt;
import com.wittywhiscash.justanotherfunctionalfoodattempt.blocks.ModBlocks;
import com.wittywhiscash.justanotherfunctionalfoodattempt.setup.FoodTiers;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(JustAnotherFunctionalFoodAttempt.MODID)
public class ModItems {
    private static Item.Properties PROPERTIES = new Item.Properties().group(JustAnotherFunctionalFoodAttempt.ITEM_GROUP);

    // Food
    @ObjectHolder("garlic") public static final Item GARLIC = null;
    @ObjectHolder("tomato") public static final Item TOMATO = null;
    @ObjectHolder("lettuce") public static final Item LETTUCE = null;

    // Food Seeds
    @ObjectHolder("garlic_seeds") public static final Item GARLIC_SEEDS = null;
    @ObjectHolder("tomato_seeds") public static final Item TOMATO_SEEDS = null;
    @ObjectHolder("lettuce_seeds") public static final Item LETTUCE_SEEDS = null;


    //Block Items
    @ObjectHolder("trellis") public static final Item TRELLIS = null;


    // Register Items
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
        itemRegistryEvent.getRegistry().registerAll(
                new FoodItem(FoodTiers.GARLIC).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("garlic")),
                new FoodItem(FoodTiers.TOMATO).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("tomato")),
                new FoodItem(FoodTiers.LETTUCE).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("lettuce")),

                new CustomSeedsItem(ModBlocks.GARLIC_CROP).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("garlic_seeds")),
                new TrellisSeedsItem(ModBlocks.TOMATO_CROP).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("tomato_seeds")),
                new CustomSeedsItem(ModBlocks.LETTUCE_CROP).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("lettuce_seeds")),


                new BlockItem(ModBlocks.TRELLIS, PROPERTIES).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("trellis")),
            );
        }
}
