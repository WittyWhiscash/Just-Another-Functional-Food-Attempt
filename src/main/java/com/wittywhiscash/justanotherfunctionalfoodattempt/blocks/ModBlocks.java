package com.wittywhiscash.justanotherfunctionalfoodattempt.blocks;

import com.wittywhiscash.justanotherfunctionalfoodattempt.JustAnotherFunctionalFoodAttempt;
import com.wittywhiscash.justanotherfunctionalfoodattempt.items.ModItems;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(JustAnotherFunctionalFoodAttempt.MODID)
public class ModBlocks {

    // Crops
    @ObjectHolder("garlic_crop") public static final Block GARLIC_CROP = null;
    @ObjectHolder("tomato_crop") public static final Block TOMATO_CROP = null;

    // Blocks
    @ObjectHolder("trellis") public static final Block TRELLIS = null;

    // Register Blocks
    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> blockRegistryEvent) {
        blockRegistryEvent.getRegistry().registerAll(
                new CustomCrop().setRegistryName(JustAnotherFunctionalFoodAttempt.getId("garlic_crop")),
                new CustomTrellisCrop(() -> ModItems.TOMATO, () -> ModItems.TOMATO_SEEDS).setRegistryName(JustAnotherFunctionalFoodAttempt.getId("tomato_crop")),
                new TrellisBlock().setRegistryName(JustAnotherFunctionalFoodAttempt.getId("trellis"))
        );
    }
}
