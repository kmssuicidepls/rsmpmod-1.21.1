package com.kmssuicidepls.rsmpmod.block.custom;

import com.kmssuicidepls.rsmpmod.item.ModItems;
import com.kmssuicidepls.rsmpmod.block.ModBlocks;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

public class FleshAnvilMenu extends AnvilMenu {

    private static final int BLOOD_STAR_COST = 10; // XP levels required — tune to taste
    private boolean craftingBloodStar = false;

    public FleshAnvilMenu(int containerId, Inventory playerInventory) {
        super(containerId, playerInventory);
    }

    public FleshAnvilMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(containerId, playerInventory, access);
    }

    @Override
    protected boolean isValidBlock(BlockState state) {
        return state.is(ModBlocks.FLESH_ANVIL.get());
    }

    @Override
    public void createResult() {
        ItemStack left = this.inputSlots.getItem(0);
        ItemStack right = this.inputSlots.getItem(1);

        if (left.is(ModItems.RAT_CHUNK.get()) && right.is(Items.NETHER_STAR)) {
            this.craftingBloodStar = true;
            this.resultSlots.setItem(0, new ItemStack(ModItems.BLOODSTAR.get()));
            return;
        }

        this.craftingBloodStar = false;
        super.createResult();
    }

    @Override
    protected boolean mayPickup(Player player, boolean hasSecondItem) {
        if (this.craftingBloodStar) {
            return player.getAbilities().instabuild || player.experienceLevel >= BLOOD_STAR_COST;
        }
        return super.mayPickup(player, hasSecondItem);
    }

    @Override
    protected void onTake(Player player, ItemStack stack) {
        if (this.craftingBloodStar) {
            if (!player.getAbilities().instabuild) {
                player.giveExperienceLevels(-BLOOD_STAR_COST);
            }
            this.inputSlots.setItem(0, ItemStack.EMPTY);
            ItemStack right = this.inputSlots.getItem(1);
            right.shrink(1);
            this.inputSlots.setItem(1, right);
            this.craftingBloodStar = false;
            return;
        }
        super.onTake(player, stack);
    }
}