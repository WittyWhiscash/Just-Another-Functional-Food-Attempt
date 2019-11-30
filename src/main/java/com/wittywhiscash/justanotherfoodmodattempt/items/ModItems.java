package com.wittywhiscash.justanotherfoodmodattempt.items;

import com.wittywhiscash.justanotherfoodmodattempt.JustAnotherFoodModAttempt;
import com.wittywhiscash.justanotherfoodmodattempt.blocks.ModBlocks;
import com.wittywhiscash.justanotherfoodmodattempt.setup.FoodTiers;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(JustAnotherFoodModAttempt.MODID)
public class ModItems {
    private static Item.Properties PROPERTIES = new Item.Properties().group(JustAnotherFoodModAttempt.ITEM_GROUP);

    // Food
    @ObjectHolder("garlic") public static final Item GARLIC = null;
    @ObjectHolder("tomato") public static final Item TOMATO = null;

    // Food Seeds
    @ObjectHolder("garlic_seeds") public static final Item GARLIC_SEEDS = null;
    @ObjectHolder("tomato_seeds") public static final Item TOMATO_SEEDS = null;

    //Block Items
    @ObjectHolder("trellis") public static final Item TRELLIS = null;



    // Register Items
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> itemRegistryEvent) {
        itemRegistryEvent.getRegistry().registerAll(
                new FoodItem(FoodTiers.GARLIC).setRegistryName(JustAnotherFoodModAttempt.getId("garlic")),
                new FoodItem(FoodTiers.TOMATO).setRegistryName(JustAnotherFoodModAttempt.getId("tomato")),
                new CustomSeedsItem(ModBlocks.GARLIC_CROP).setRegistryName(JustAnotherFoodModAttempt.getId("garlic_seeds")),
                new TrellisSeedsItem(ModBlocks.TOMATO_CROP).setRegistryName(JustAnotherFoodModAttempt.getId("tomato_seeds")),
                new BlockItem(ModBlocks.TRELLIS, PROPERTIES).setRegistryName(JustAnotherFoodModAttempt.getId("trellis"))
        );
        }
}
