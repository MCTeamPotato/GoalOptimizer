package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.teampotato.goaloptimizer.GoalOptimizer.pufferfishHasPufferGoal;

@Mixin(value = PufferfishEntity.class, priority = 10)
public abstract class MixinPufferfishEntity extends AbstractFishEntity {
    public MixinPufferfishEntity(EntityType<? extends AbstractFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/entity/ai/goal/Goal;)V"))
    private void onPuffGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (pufferfishHasPufferGoal.get()) instance.addGoal(pPriority, pTask);
    }
}