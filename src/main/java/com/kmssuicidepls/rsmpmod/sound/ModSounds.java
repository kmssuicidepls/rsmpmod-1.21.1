package com.kmssuicidepls.rsmpmod.sound;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, RsmpMod.MOD_ID);

    public static final Supplier<SoundEvent> JUSTJO_WALK = registerSoundEvent("justjo_walk");
    public static final Supplier<SoundEvent> JUSTJO_LEAP = registerSoundEvent("justjo_leap");

    public static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(RsmpMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}