package com.kmssuicidepls.rsmpmod.entity.client;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.entity.custom.JustjoEntity;
import com.kmssuicidepls.rsmpmod.entity.custom.RatEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class JustjoModel<T extends JustjoEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RsmpMod.MOD_ID, "justjo"), "main");
    private final ModelPart controller;
    private final ModelPart head;

    public JustjoModel(ModelPart root) {
        this.controller = root.getChild("controller");
        this.head = this.controller.getChild("torso").getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition controller = partdefinition.addOrReplaceChild("controller", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition torso = controller.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -8.0F, -1.0F, 15.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-7.5F, -15.0F, -1.0F, 15.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, -1.0F));

        PartDefinition neck_r1 = head.addOrReplaceChild("neck_r1", CubeListBuilder.create().texOffs(14, 38).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition cranium = head.addOrReplaceChild("cranium", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -6.0F, -1.7F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.3F));

        PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(8.0F, -14.0F, 0.0F));

        PartDefinition left_bicep_r1 = left_arm.addOrReplaceChild("left_bicep_r1", CubeListBuilder.create().texOffs(18, 22).addBox(-1.0F, -2.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2546F, 0.4114F, 0.577F));

        PartDefinition left_forearm = left_arm.addOrReplaceChild("left_forearm", CubeListBuilder.create(), PartPose.offset(5.0F, 4.0F, -3.0F));

        PartDefinition left_forearm_r1 = left_forearm.addOrReplaceChild("left_forearm_r1", CubeListBuilder.create().texOffs(18, 30).addBox(-1.0F, -2.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.32F, 0.7466F, 0.6946F));

        PartDefinition left_hand = left_forearm.addOrReplaceChild("left_hand", CubeListBuilder.create(), PartPose.offset(5.0F, 3.0F, -6.0F));

        PartDefinition left_thumb_r1 = left_hand.addOrReplaceChild("left_thumb_r1", CubeListBuilder.create().texOffs(22, 43).addBox(-0.6626F, -0.2071F, -2.3975F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -2.0F, 0.8414F, 0.7493F, 0.3599F));

        PartDefinition left_pinky_r1 = left_hand.addOrReplaceChild("left_pinky_r1", CubeListBuilder.create().texOffs(42, 38).addBox(-0.5F, -0.5F, -3.2044F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.9384F, 0.0F, -1.0F, 0.2618F, -0.6981F, 0.0F));

        PartDefinition left_index_r1 = left_hand.addOrReplaceChild("left_index_r1", CubeListBuilder.create().texOffs(42, 34).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9384F, 0.0F, -3.0F, 0.2618F, -0.6981F, 0.0F));

        PartDefinition left_palm_r1 = left_hand.addOrReplaceChild("left_palm_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-1.6428F, -0.5F, -2.766F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6981F, 0.0F));

        PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-8.0F, -14.0F, 0.0F));

        PartDefinition right_bicep_r1 = right_arm.addOrReplaceChild("right_bicep_r1", CubeListBuilder.create().texOffs(0, 34).addBox(-8.0F, -2.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2546F, -0.4114F, -0.577F));

        PartDefinition right_forearm = right_arm.addOrReplaceChild("right_forearm", CubeListBuilder.create(), PartPose.offset(-5.0F, 4.0F, -3.0F));

        PartDefinition right_forearm_r1 = right_forearm.addOrReplaceChild("right_forearm_r1", CubeListBuilder.create().texOffs(18, 26).addBox(-8.0F, -2.0F, -1.0F, 9.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.32F, -0.7466F, -0.6946F));

        PartDefinition right_hand = right_forearm.addOrReplaceChild("right_hand", CubeListBuilder.create(), PartPose.offset(-5.0F, 3.0F, -6.0F));

        PartDefinition right_thumb_r1 = right_hand.addOrReplaceChild("right_thumb_r1", CubeListBuilder.create().texOffs(0, 42).addBox(-0.3374F, -0.2071F, -2.3975F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, -2.0F, 0.8414F, -0.7493F, -0.3599F));

        PartDefinition right_pinky_r1 = right_hand.addOrReplaceChild("right_pinky_r1", CubeListBuilder.create().texOffs(42, 42).addBox(-0.5F, -0.5F, -3.2044F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9384F, 0.0F, -1.0F, 0.2618F, 0.6981F, 0.0F));

        PartDefinition right_index_r1 = right_hand.addOrReplaceChild("right_index_r1", CubeListBuilder.create().texOffs(30, 43).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9384F, 0.0F, -3.0F, 0.2618F, 0.6981F, 0.0F));

        PartDefinition right_palm_r1 = right_hand.addOrReplaceChild("right_palm_r1", CubeListBuilder.create().texOffs(40, 20).addBox(-2.3572F, -0.5F, -2.766F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6981F, 0.0F));

        PartDefinition dihh = torso.addOrReplaceChild("dihh", CubeListBuilder.create().texOffs(40, 24).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -1.0F));

        PartDefinition dihh_tip = dihh.addOrReplaceChild("dihh_tip", CubeListBuilder.create().texOffs(40, 29).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition left_leg = controller.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 34).addBox(2.0F, 6.0F, -1.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -13.0F, 0.0F));

        PartDefinition left_thigh_r1 = left_leg.addOrReplaceChild("left_thigh_r1", CubeListBuilder.create().texOffs(34, 0).addBox(0.5F, -8.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 8.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition right_leg = controller.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(22, 34).addBox(-5.0F, 6.0F, -1.0F, 3.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -13.0F, 0.0F));

        PartDefinition right_thigh_r1 = right_leg.addOrReplaceChild("right_thigh_r1", CubeListBuilder.create().texOffs(34, 10).addBox(-3.5F, -8.0F, -1.0F, 3.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.48F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }


    @Override
    public void setupAnim(JustjoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        {
            this.root().getAllParts().forEach(ModelPart::resetPose);

            this.applyHeadRotation(netHeadYaw, headPitch);
            this.animateWalk(JustjoAnimations.justjo_move, limbSwing, limbSwingAmount, 1f, 1f);
            this.animate(entity.attackAnimationState, JustjoAnimations.justjo_attack, ageInTicks, 1f);
            this.animate(entity.idleAnimationState, JustjoAnimations.justjo_idle, ageInTicks, 1f);
            this.animate(entity.leapAnimationState, JustjoAnimations.justjo_leap, ageInTicks, 1f);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        controller.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return controller;
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}