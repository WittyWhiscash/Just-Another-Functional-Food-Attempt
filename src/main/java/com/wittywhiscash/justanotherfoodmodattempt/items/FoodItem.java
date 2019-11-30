package com.wittywhiscash.justanotherfoodmodattempt.items;

import com.wittywhiscash.justanotherfoodmodattempt.JustAnotherFoodModAttempt;
import com.wittywhiscash.justanotherfoodmodattempt.setup.FoodTiers;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class FoodItem extends Item {

    // Constructor for Food Item.
    public FoodItem(Food foodtier) {
        super(new Item.Properties().maxStackSize(64).food(foodtier).group(JustAnotherFoodModAttempt.ITEM_GROUP));
    }
}
