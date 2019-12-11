package com.wittywhiscash.justanotherfunctionalfoodattempt.items;

import com.wittywhiscash.justanotherfunctionalfoodattempt.JustAnotherFunctionalFoodAttempt;
import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class CustomSeedsItem extends BlockNamedItem {

    // Stores the crop that gets called on initialization.
    private Block CROP;

    // Constructor for Custom Seeds Item.
    public CustomSeedsItem(Block crop) {
        super(crop, new Item.Properties().maxStackSize(64).group(JustAnotherFunctionalFoodAttempt.ITEM_GROUP));
        this.CROP = crop;
    }
}
