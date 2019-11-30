package com.wittywhiscash.justanotherfoodmodattempt.items;

import com.wittywhiscash.justanotherfoodmodattempt.JustAnotherFoodModAttempt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class CustomSeedsItem extends BlockNamedItem {

    // Stores the crop that gets called on initialization.
    private Block CROP;

    // Constructor for Custom Seeds Item.
    public CustomSeedsItem(Block crop) {
        super(crop, new Item.Properties().maxStackSize(64).group(JustAnotherFoodModAttempt.ITEM_GROUP));
        this.CROP = crop;
    }
}
