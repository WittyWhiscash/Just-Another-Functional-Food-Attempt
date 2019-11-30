package com.wittywhiscash.justanotherfoodmodattempt.items;

import com.wittywhiscash.justanotherfoodmodattempt.JustAnotherFoodModAttempt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;

public class TrellisSeedsItem extends Item {

    private Block CROP;

    public TrellisSeedsItem(Block crop) {
        super(new Item.Properties().maxStackSize(64).group(JustAnotherFoodModAttempt.ITEM_GROUP));
        this.CROP = crop;
    }

    public BlockState getCropState() {
        return CROP.getDefaultState();
    }
}
