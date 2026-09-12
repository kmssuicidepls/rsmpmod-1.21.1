package com.kmssuicidepls.rsmpmod.entity.custom;

import com.kmssuicidepls.rsmpmod.sound.ModSounds;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class JustjoLeapAttackGoal extends Goal {
    private final JustjoEntity justjo;
    private final double leapDamage;
    private static final double MIN_LEAP_DIST_SQR = 28.0;  // 7 blocks
    private static final double MAX_LEAP_DIST_SQR = 200.0; // 20 blocks
    private static final int COOLDOWN_TICKS = 60; // 3s between leaps

    private int cooldown = 0;
    private boolean leaping = false;
    private boolean hasHit = false;
    private LivingEntity target;

    public JustjoLeapAttackGoal(JustjoEntity justjo, double leapDamage) {
        this.justjo = justjo;
        this.leapDamage = leapDamage;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (cooldown > 0) {
            cooldown--;
            return false;
        }
        LivingEntity potentialTarget = this.justjo.getTarget();
        if (potentialTarget == null || !potentialTarget.isAlive() || !this.justjo.onGround()) {
            return false;
        }
        double distSqr = this.justjo.distanceToSqr(potentialTarget);
        return distSqr >= MIN_LEAP_DIST_SQR && distSqr <= MAX_LEAP_DIST_SQR;
    }

    @Override
    public boolean canContinueToUse() {
        return this.leaping && !this.justjo.onGround();
    }

    @Override
    public void start() {
        this.target = this.justjo.getTarget();
        this.hasHit = false;
        if (this.target == null) return;

        Vec3 toTarget = new Vec3(
                this.target.getX() - this.justjo.getX(),
                0,
                this.target.getZ() - this.justjo.getZ()
        );
        double horizontalDist = toTarget.horizontalDistance();
        if (horizontalDist > 1.0E-4) {
            Vec3 normalized = toTarget.normalize();
            double horizontalPower = Math.min(horizontalDist * 0.4, 1.5);
            this.justjo.setDeltaMovement(
                    normalized.x * horizontalPower,
                    0.55, // vertical leap strength — raise for a higher pounce
                    normalized.z * horizontalPower
            );
        }

        this.leaping = true;
        this.justjo.triggerLeapAnimation();
        this.leaping = true;
        this.justjo.triggerLeapAnimation();
        this.justjo.playSound(ModSounds.JUSTJO_LEAP.get(), 1.0F, 1.0F);
    }

    @Override
    public void tick() {
        if (this.target == null || !this.target.isAlive() || this.hasHit) {
            return;
        }
        // Land the hit once, when close enough mid-air, so it lines up with the pounce.
        if (this.justjo.distanceToSqr(this.target) <= 4.0) {
            this.justjo.doHurtTarget(this.target);
            this.hasHit = true;
        }
    }

    @Override
    public void stop() {
        this.leaping = false;
        this.cooldown = COOLDOWN_TICKS;
    }
}