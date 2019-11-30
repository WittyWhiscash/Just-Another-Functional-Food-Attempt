package com.wittywhiscash.justanotherfunctionalfoodattempt.blocks;

import com.wittywhiscash.justanotherfunctionalfoodattempt.JustAnotherFunctionalFoodAttempt;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.function.Supplier;

public class CustomTrellisCrop extends Block implements IGrowable, IPlantable {

    // Define the age property for the trellis crop.
    public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;

    // Define the collision box for the trellis crop fence in the middle.
    private final VoxelShape COLLISION_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    // Define suppliers of items for when the crop needs to get seeds and crops.
    private Supplier<Item> CROP_GETTER;
    private Supplier<Item> SEEDS_GETTER;

    // Constructor for Custom Trellis Crop.
    public CustomTrellisCrop(Supplier<Item> crop, Supplier<Item> seeds) {
        super(Block.Properties.create(Material.PLANTS)
                .tickRandomly()
                .hardnessAndResistance(1F)
                .sound(SoundType.CROP)
        );

        // Set suppliers to variables from constructor.
        this.CROP_GETTER = crop;
        this.SEEDS_GETTER = seeds;

        // Set the default state when planted to 0.
        this.setDefaultState(this.getDefaultState().with(AGE, Integer.valueOf(0)));
    }

    // Maximum height this crop can grow on a trellis.
    public int getMaxHeight() {
        return 3;
    }

    // Maximum age it takes for the crop to be fully grown.
    public int getMaxAge() {
        return 7;
    }

    // Gets the crop item AFTER items have been initialized, important since this block is initialized first.
    protected Item getCrop() {
        return CROP_GETTER.get();
    }

    // Gets the seeds item AFTER items have been initialized, important since this block is initialized first.
    protected Item getSeed() {
        return SEEDS_GETTER.get();
    }

    // Return the collision box of the trellis inside the crop.
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COLLISION_SHAPE;
    }

    // Tell the model renderer that there is transparency in our model and to render the transparency as transparent.
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    // Tell the block to send light from the sky down to the ground.
    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    // What the block does on tick.
    @Override
    public void randomTick(BlockState state, World worldIn, BlockPos pos, Random random) {
        super.randomTick(state, worldIn, pos, random);

        // Check if we are valid, and drop our drops if not.
        this.checkAndDropBlock(worldIn, pos, state);

        // Check the blocks around us for light. If we are sufficiently lit, continue on.
        if (worldIn.getLight(pos.north()) >= 8 || worldIn.getLight(pos.south()) >= 8 || worldIn.getLight(pos.east()) >= 8 || worldIn.getLight(pos.west()) >= 8) {
            int i = state.get(AGE);

            // Check if the plant is not aged completely, if so, get the growth chance and grow if the chance works.
            if (i < getMaxAge()) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlockState(pos, state.with(AGE, i + 1));
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            // Check if there is a trellis above, if the soil block is a soil block, and if the age property is at least 2 or higher. If so, grow to the next trellis.
            } else if (worldIn.getBlockState(pos.up()) == ModBlocks.TRELLIS.getDefaultState() && worldIn.getBlockState(pos.down(getMaxHeight() - 1)).getBlock() != this && worldIn.getBlockState(pos).get(AGE) >= 2) {
                float f = getGrowthChance(this, worldIn, pos);
                if(net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int) (25.0F / f) + 1) == 0)) {
                    worldIn.setBlockState(pos.up(), state.with(AGE, 0));
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    // When right clicked at max age, drop a crop.
    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
        if (state.get(AGE) >= getMaxAge()) {
            world.setBlockState(pos, state.with(AGE, getMaxAge() - 1));
            Item heldItem = player.getHeldItem(hand).getItem();
            ItemStack heldItemStack = player.getHeldItem(hand);

            // Check if the player is holding a hoe. If so, modify the drop count in equivalency to something like fortune and damage the hoe.
            if (ItemTags.getCollection().get(JustAnotherFunctionalFoodAttempt.getHoeTag()).contains(heldItem)) {
                Block.spawnAsEntity(world, pos.offset(result.getFace()), new ItemStack(getCrop(), 1 + RANDOM.nextInt(3)));
                heldItemStack.damageItem(1, player, playerEntity -> {
                    playerEntity.sendBreakAnimation(hand);
                });
                return true;
            }
            else {
                Block.spawnAsEntity(world, pos.offset(result.getFace()), new ItemStack(getCrop()));
                return true;
            }
        }
        return false;
    }

    // On breaking this block, replace with a normal trellis and spawn our drops.
    @Override
    public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, null, stack);
        worldIn.setBlockState(pos, ModBlocks.TRELLIS.getDefaultState());
    }

    // Provides a number which determines whether we can grow or not.
    private static float getGrowthChance(Block block, World world, BlockPos pos) {
        float growth = 0.125F * world.getLightValue(pos);
        BlockState soil = world.getBlockState(pos.add(0, -1, 0));
        if (soil.getBlock().isFertile(soil, world, pos.add(0,-1,0)) || soil.getBlock() == block) {
            growth *= 1.5F;
        }
        return 3.5F + growth;
    }

    // If the block cannot stay, drop our drops and set it to a trellis.
    private void checkAndDropBlock(World world, BlockPos pos, BlockState state) {
        if (!this.canBlockStay(world, pos, state)) {
            spawnDrops(state, world, pos);
            world.setBlockState(pos, ModBlocks.TRELLIS.getDefaultState());
        }
    }

    // Return true if we can grow.
    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < getMaxAge();
    }

    // Return true if we can use bonemeal.
    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    // Method to increase the age of the crop.
    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
        int i = state.get(AGE) + this.getBonemealAgeIncrease();
        int j = getMaxAge();

        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, state.with(AGE, i));
    }

    // Get how much the age increases for your bonemeal usage.
    private int getBonemealAgeIncrease() {
        return 1 + RANDOM.nextInt(4);
    }

    // Get the default state of the crop block.
    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return this.getDefaultState();
    }

    // Check if we can stay. Returns true if the block has a light of 8 or more OR if it can see the sky and if the block below is an instance of this OR the block below (usually referring to dirt/grass) can sustain a plant.
    public boolean canBlockStay(World world, BlockPos pos, BlockState state) {
        BlockState soil = world.getBlockState(pos.down());
        return (world.getLight(pos) >= 8 ||  world.canBlockSeeSky(pos) && (soil.getBlock() == this) || soil.getBlock().canSustainPlant(soil, world, pos, Direction.UP, this));
    }

    // Give the block an age property.
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.AGE_0_7);
    }

}
