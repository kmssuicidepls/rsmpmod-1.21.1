package com.kmssuicidepls.rsmpmod.entity;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.entity.custom.RatEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, RsmpMod.MOD_ID);

    public static final Supplier<EntityType<RatEntity>> RAT =
            ENTITY_TYPES.register("rat", () -> EntityType.Builder.of(RatEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.3f).build("rat"));

            public static void register(IEventBus eventBus) {
               ENTITY_TYPES.register(eventBus);
            }
}