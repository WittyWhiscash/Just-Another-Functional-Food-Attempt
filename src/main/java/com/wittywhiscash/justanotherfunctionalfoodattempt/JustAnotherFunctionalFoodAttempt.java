package com.wittywhiscash.justanotherfunctionalfoodattempt;

import com.wittywhiscash.justanotherfunctionalfoodattempt.items.ModItems;
import com.wittywhiscash.justanotherfunctionalfoodattempt.setup.ModItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("justanotherfunctionalfoodattempt")
public class JustAnotherFunctionalFoodAttempt
{
    // Define static final items such as the logger, the mod ID, and the item group.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "justanotherfunctionalfoodattempt";
    public static final ItemGroup ITEM_GROUP = new ModItemGroup(JustAnotherFunctionalFoodAttempt.MODID, () -> new ItemStack(ModItems.TOMATO));

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

    public JustAnotherFunctionalFoodAttempt() {
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
