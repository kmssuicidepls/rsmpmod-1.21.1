package com.kmssuicidepls.rsmpmod.entity;

import net.minecraft.util.RandomSource;

import java.util.Arrays;
import java.util.Comparator;

public enum RatVariant {
    GREY(0),
    ALBINO(1);

    private static final RatVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(RatVariant::getId)).toArray(RatVariant[]::new);
    private final int id;

    RatVariant(int id){
        this.id = id;
    }

    public static RatVariant getWeightedRandomVariant(RandomSource random) {
        return random.nextInt(50) == 0 ? ALBINO : GREY;
    }

    public int getId() {
        return id;
    }

    public static RatVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
