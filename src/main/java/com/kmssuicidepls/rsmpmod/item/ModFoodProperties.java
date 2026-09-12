package com.kmssuicidepls.rsmpmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties COOKED_RAT_SANDWICH = new FoodProperties.Builder().nutrition(8).saturationModifier(1f).build();
    public static final FoodProperties COOKED_RAT_CHUNK = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).build();
    public static final FoodProperties RAT_CHUNK = new FoodProperties.Builder().nutrition(3).saturationModifier(0.3f)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200), 0.5f).build();



}
