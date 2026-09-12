package com.kmssuicidepls.rsmpmod.entity.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class JustjoAttackGoal extends MeleeAttackGoal {
    private final JustjoEntity justjo;
    private static final int WINDUP_TICKS = 1;
    private int windupTicksLeft = -1;

    public JustjoAttackGoal(JustjoEntity justjo, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(justjo, speedModifier, followingTargetEvenIfNotSeen);
        this.justjo = justjo;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity target) {
        if (windupTicksLeft < 0) {
            if (this.canPerformAttack(target)) {
                this.resetAttackCooldown();
                this.windupTicksLeft = WINDUP_TICKS;
                this.justjo.triggerAttackAnimation();
            }
            return;
        }

        this.windupTicksLeft--;
        if (this.windupTicksLeft == 0) {
            this.justjo.swing(InteractionHand.MAIN_HAND);
            this.justjo.doHurtTarget(target);
            this.windupTicksLeft = -1;
        }
    }

    @Override
    public void stop() {
        super.stop();
        LivingEntity target = this.justjo.getTarget();
        if (target == null || !target.isAlive()) {
            this.windupTicksLeft = -1;
        }
    }

    @Override
    protected boolean canPerformAttack(LivingEntity target) {
        double reachSqr = (this.justjo.getBbWidth() * 2.0F * this.justjo.getBbWidth() * 2.0F) + target.getBbWidth();
        return this.getTicksUntilNextAttack() <= 5
                && this.justjo.distanceToSqr(target.getX(), target.getY(), target.getZ()) <= reachSqr
                && this.justjo.hasLineOfSight(target);
    }
}
