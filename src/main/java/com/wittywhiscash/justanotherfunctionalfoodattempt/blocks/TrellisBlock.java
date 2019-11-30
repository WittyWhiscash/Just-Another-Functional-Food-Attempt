package com.wittywhiscash.justanotherfunctionalfoodattempt.blocks;

import com.wittywhiscash.justanotherfunctionalfoodattempt.items.TrellisSeedsItem;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class TrellisBlock extends Block {

    // Variable to hold our hit box for the trellis.
    private final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    // Constructor for Trellis Block
    public TrellisBlock() {
        super(Block.Properties.create(Material.PLANTS)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(2.0F, 5.0F)
                .harvestTool(ToolType.AXE)
        );
    }

    // Return the hit box and collision box of the trellis.
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    // If we right click the block with an instance of a custom seed, change the state of the block to a custom trellis crop of type crop registered to the seeds.
    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        ItemStack stack = player.getHeldItem(hand);
        if (!stack.isEmpty() && stack.getItem() instanceof TrellisSeedsItem) {
                world.setBlockState(pos, ((TrellisSeedsItem) stack.getItem()).getCropState());
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                return true;
            }
        return false;
    }

    // Check if the block can be placed.
    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState validBlock = worldIn.getBlockState(pos.down());
        return validBlock.getBlock() == Blocks.FARMLAND|| validBlock.getBlock() == ModBlocks.TRELLIS || validBlock.getBlock() == ModBlocks.TOMATO_CROP;
    }

}
