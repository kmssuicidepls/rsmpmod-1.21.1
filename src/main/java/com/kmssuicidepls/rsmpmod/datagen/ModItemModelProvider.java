package com.kmssuicidepls.rsmpmod.datagen;


import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RsmpMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.BLOODSTAR.get());
        basicItem(ModItems.RAT_CHUNK.get());
        basicItem(ModItems.COOKED_RAT_CHUNK.get());
        basicItem(ModItems.COOKED_RAT_SANDWICH.get());
    }
}