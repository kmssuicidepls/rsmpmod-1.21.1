package com.kmssuicidepls.rsmpmod.item;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RsmpMod.MOD_ID);

    public static final DeferredItem<Item> BLOODSTAR = ITEMS.register("bloodstar",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAT_CHUNK = ITEMS.register("rat_chunk",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RAT_CHUNK)));

    public static final DeferredItem<Item> COOKED_RAT_CHUNK = ITEMS.register("cooked_rat_chunk",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_RAT_CHUNK)));

    public static final DeferredItem<Item> COOKED_RAT_SANDWICH = ITEMS.register("cooked_rat_sandwich",
            () -> new Item(new Item.Properties().food(ModFoodProperties.COOKED_RAT_SANDWICH)));

    public static final DeferredItem<Item> RAT_SPAWN_EGG = ITEMS.register("rat_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.RAT, 0xFFFFFF, 0xFFFFFF,
                    new Item.Properties()));

    public static final DeferredItem<Item> JUSTJO_SPAWN_EGG = ITEMS.register("justjo_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.JUSTJO, 0xFFFFFF, 0xFFFFFF,
                    new Item.Properties()));


    public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
}
}
