package com.wittywhiscash.justanotherfunctionalfoodattempt.blocks;

import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CustomCrop extends CropsBlock {

    // Constructor for Custom Crop.
    public CustomCrop() {
        super(Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0)
                .sound(SoundType.CROP)
        );
        // Set the default age when this crop is spawned to 0.
        this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
    }

}
