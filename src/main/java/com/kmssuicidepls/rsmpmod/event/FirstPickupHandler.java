package com.kmssuicidepls.rsmpmod.event;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

@EventBusSubscriber(modid = RsmpMod.MOD_ID)
public class FirstPickupHandler {

    private static final String NBT_KEY = "rsmpmod_seen_rat_chunk";

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        ItemStack stack = event.getOriginalStack();

        if (!stack.is(ModItems.RAT_CHUNK.get())) {
            return;
        }

        CompoundTag data = player.getPersistentData();
        if (!data.getBoolean(NBT_KEY)) {
            data.putBoolean(NBT_KEY, true);
            player.sendSystemMessage(Component.literal("Du günstige ratte..."));
        }
    }
}