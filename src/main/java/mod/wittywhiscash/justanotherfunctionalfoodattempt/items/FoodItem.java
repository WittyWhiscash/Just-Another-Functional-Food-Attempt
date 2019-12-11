package com.wittywhiscash.justanotherfunctionalfoodattempt.items;

import com.wittywhiscash.justanotherfunctionalfoodattempt.JustAnotherFunctionalFoodAttempt;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class FoodItem extends Item {

    // Constructor for Food Item.
    public FoodItem(Food foodtier) {
        super(new Item.Properties().maxStackSize(64).food(foodtier).group(JustAnotherFunctionalFoodAttempt.ITEM_GROUP));
    }
}
