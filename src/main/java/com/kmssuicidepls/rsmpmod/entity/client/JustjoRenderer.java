package com.kmssuicidepls.rsmpmod.entity.client;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.entity.custom.JustjoEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JustjoRenderer extends MobRenderer<JustjoEntity, JustjoModel<JustjoEntity>> {
    public JustjoRenderer(EntityRendererProvider.Context context) {
        super(context, new JustjoModel<>(context.bakeLayer(JustjoModel.LAYER_LOCATION)),1f);
    }

    @Override
    public ResourceLocation getTextureLocation(JustjoEntity justjoEntity) {
        return ResourceLocation.fromNamespaceAndPath(RsmpMod.MOD_ID,"textures/entity/justjo/justjo.png");
    }

    @Override
    public void render(JustjoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(1f, 1f, 1f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}