package com.kmssuicidepls.rsmpmod.event;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.entity.ModEntities;
import com.kmssuicidepls.rsmpmod.entity.client.JustjoModel;
import com.kmssuicidepls.rsmpmod.entity.client.RatModel;
import com.kmssuicidepls.rsmpmod.entity.custom.JustjoEntity;
import com.kmssuicidepls.rsmpmod.entity.custom.RatEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class ModEventBusEvent {
    @EventBusSubscriber(modid = RsmpMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(RatModel.LAYER_LOCATION, RatModel::createBodyLayer);
            event.registerLayerDefinition(JustjoModel.LAYER_LOCATION, JustjoModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(ModEntities.RAT.get(), RatEntity.createAttributes().build());
            event.put(ModEntities.JUSTJO.get(), JustjoEntity.createAttributes().build());
        }


    }
}
