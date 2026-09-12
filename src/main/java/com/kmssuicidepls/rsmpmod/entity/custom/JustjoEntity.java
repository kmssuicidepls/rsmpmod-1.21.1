package com.kmssuicidepls.rsmpmod.entity.custom;

import com.kmssuicidepls.rsmpmod.RsmpMod;
import com.kmssuicidepls.rsmpmod.entity.ModEntities;
import com.kmssuicidepls.rsmpmod.entity.RatVariant;
import com.kmssuicidepls.rsmpmod.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

public class JustjoEntity extends Monster {

    /*Attributes + Registration*/

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(JustjoEntity.class, EntityDataSerializers.INT);

    public JustjoEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(1, new JustjoLeapAttackGoal(this, 8.0));
        this.goalSelector.addGoal(2, new JustjoAttackGoal(this, 2, false));


        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200d)
                .add(Attributes.MOVEMENT_SPEED, .20d)
                .add(Attributes.ATTACK_DAMAGE, 12.0D)
                .add(Attributes.SAFE_FALL_DISTANCE, 10)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    /*ANIMATIONS + ATTACKS*/

    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState leapAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final EntityDataAccessor<Integer> ATTACK_ANIM_TRIGGER =
            SynchedEntityData.defineId(JustjoEntity.class, EntityDataSerializers.INT);
    private int lastSeenAttackAnimTrigger = 0;

    public void triggerAttackAnimation() {
        this.entityData.set(ATTACK_ANIM_TRIGGER, this.entityData.get(ATTACK_ANIM_TRIGGER) + 1);
    }

    private static final EntityDataAccessor<Integer> LEAP_ANIM_TRIGGER =
            SynchedEntityData.defineId(JustjoEntity.class, EntityDataSerializers.INT);
    private int lastSeenLeapAnimTrigger = 0;

    public void triggerLeapAnimation() {
        this.entityData.set(LEAP_ANIM_TRIGGER, this.entityData.get(LEAP_ANIM_TRIGGER) + 1);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 23;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        int currentTrigger = this.entityData.get(ATTACK_ANIM_TRIGGER);
        if (currentTrigger != this.lastSeenAttackAnimTrigger) {
            this.lastSeenAttackAnimTrigger = currentTrigger;
            this.attackAnimationState.start(this.tickCount);
        }

        int currentLeapTrigger = this.entityData.get(LEAP_ANIM_TRIGGER);
        if (currentLeapTrigger != this.lastSeenLeapAnimTrigger) {
            this.lastSeenLeapAnimTrigger = currentLeapTrigger;
            this.leapAnimationState.start(this.tickCount);
        }
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
        builder.define(ATTACK_ANIM_TRIGGER, 0);
        builder.define(LEAP_ANIM_TRIGGER, 0);
    }

    /*SOUNDS*/

    private int walkSoundCooldown = 0;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }

        if (this.walkSoundCooldown > 0) {
            this.walkSoundCooldown--;
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (this.walkSoundCooldown <= 0) {
            this.playSound(ModSounds.JUSTJO_WALK.get(), 0.15F, 1.0F);
            this.walkSoundCooldown = 40;
        }
    }

    /*Boss Bar*/

    public final ServerBossEvent bossEvent =
            new ServerBossEvent(Component.literal("The Man of the Cloth"), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.NOTCHED_10);

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);
        this.bossEvent.addPlayer(serverPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer serverPlayer) {
        super.stopSeenByPlayer(serverPlayer);
        this.bossEvent.removePlayer(serverPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}