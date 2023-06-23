package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Pufferfish;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.teampotato.goaloptimizer.GoalOptimizer.pufferfishHasPufferGoal;

@Mixin(value = Pufferfish.class, priority = 10)
public abstract class MixinPufferfishEntity extends AbstractFish {
    public MixinPufferfishEntity(EntityType<? extends AbstractFish> entityType, Level world) {
        super(entityType, world);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V"))
    private void onPuffGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (pufferfishHasPufferGoal.get()) instance.addGoal(pPriority, pTask);
    }
}