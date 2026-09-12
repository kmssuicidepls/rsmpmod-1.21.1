package com.kmssuicidepls.rsmpmod;

import com.kmssuicidepls.rsmpmod.block.ModBlocks;
import com.kmssuicidepls.rsmpmod.entity.ModEntities;
import com.kmssuicidepls.rsmpmod.entity.client.JustjoRenderer;
import com.kmssuicidepls.rsmpmod.entity.client.RatRenderer;
import com.kmssuicidepls.rsmpmod.items.ModCreativeModeTabs;
import com.kmssuicidepls.rsmpmod.items.ModItems;
import com.kmssuicidepls.rsmpmod.sound.ModSounds;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(RsmpMod.MOD_ID)
public class RsmpMod {
    public static final String MOD_ID = "rsmpmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public RsmpMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        modEventBus.addListener(this::commonSetup);

        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModSounds.register(modEventBus);

        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // e.g. add your items to a creative tab here
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // your setup code here
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.RAT.get(), RatRenderer::new);
            EntityRenderers.register(ModEntities.JUSTJO.get(), JustjoRenderer::new);
        }
    }
}