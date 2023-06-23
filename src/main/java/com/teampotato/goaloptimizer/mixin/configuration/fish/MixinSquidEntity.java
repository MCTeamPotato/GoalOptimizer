package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.teampotato.goaloptimizer.GoalOptimizer.squidHasFleeGoal;
import static com.teampotato.goaloptimizer.GoalOptimizer.squidHasMoveRandomGoal;

@Mixin(value = SquidEntity.class, priority = 10)
public abstract class MixinSquidEntity extends WaterMobEntity {
    protected MixinSquidEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 0))
    private void onMoveRandomGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (squidHasMoveRandomGoal.get()) instance.addGoal(pPriority, pTask);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 1))
    private void onFleeGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (squidHasFleeGoal.get()) instance.addGoal(pPriority, pTask);
    }
}
