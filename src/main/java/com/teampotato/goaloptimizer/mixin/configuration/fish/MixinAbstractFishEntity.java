package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = AbstractFishEntity.class, priority = 10)
public abstract class MixinAbstractFishEntity extends WaterMobEntity {
    protected MixinAbstractFishEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 0))
    private void onPanicGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (abstractFishHasPanicGoal.get()) instance.addGoal(pPriority, pTask);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 1))
    private void onAvoidEntityGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (abstractFishHasAvoidEntityGoal.get()) instance.addGoal(pPriority, pTask);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 2))
    private void onSwimGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (abstractFishHasSwimGoal.get()) instance.addGoal(pPriority, pTask);
    }
}
