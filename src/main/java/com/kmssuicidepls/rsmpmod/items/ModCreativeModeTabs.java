package com.kmssuicidepls.rsmpmod.items;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RsmpMod.MOD_ID);

    public static final Supplier<CreativeModeTab> RSMP_MOD = CREATIVE_MODE_TAB.register("rsmp_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.MILK_BUCKET))
                    .title(Component.translatable("creativetab.rsmpmod.rsmpmod"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAT_SPAWN_EGG);
                        output.accept(ModItems.BLOODSTAR);
                        output.accept(ModItems.RAT_CHUNK);

                        output.accept(ModBlocks.AMALGAM_BLOCK);
                        output.accept(ModBlocks.BLOOD_ALTER);
                        output.accept(ModBlocks.FLESH_ANVIL);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
