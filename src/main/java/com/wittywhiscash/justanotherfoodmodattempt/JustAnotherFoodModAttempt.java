package com.wittywhiscash.justanotherfoodmodattempt;

import com.wittywhiscash.justanotherfoodmodattempt.blocks.CustomCrop;
import com.wittywhiscash.justanotherfoodmodattempt.blocks.CustomTrellisCrop;
import com.wittywhiscash.justanotherfoodmodattempt.blocks.ModBlocks;
import com.wittywhiscash.justanotherfoodmodattempt.blocks.TrellisBlock;
import com.wittywhiscash.justanotherfoodmodattempt.items.*;
import com.wittywhiscash.justanotherfoodmodattempt.setup.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("justanotherfoodmodattempt")
public class JustAnotherFoodModAttempt
{
    // Define static final items such as the logger, the mod ID, and the item group.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "justanotherfoodmodattempt";
    public static final ItemGroup ITEM_GROUP = new ModItemGroup(JustAnotherFoodModAttempt.MODID, () -> new ItemStack(ModItems.TOMATO));

    // Methods to get the resource location of an item or a data pack tag.
    public static ResourceLocation getId(String name) {
        return new ResourceLocation(MODID, name);
    }
    public static ResourceLocation getToolTag() {
        return new ResourceLocation(MODID, "tools");
    }
    public static ResourceLocation getHoeTag() {
        return new ResourceLocation(MODID, "hoes");
    }

    public JustAnotherFoodModAttempt() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.debug("Finished Setup!");
    }

}
