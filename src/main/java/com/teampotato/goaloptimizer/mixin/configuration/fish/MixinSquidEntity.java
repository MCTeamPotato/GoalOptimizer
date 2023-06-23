package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.teampotato.goaloptimizer.GoalOptimizer.squidHasFleeGoal;
import static com.teampotato.goaloptimizer.GoalOptimizer.squidHasMoveRandomGoal;

@Mixin(value = Squid.class, priority = 10)
public abstract class MixinSquidEntity extends WaterAnimal {
    protected MixinSquidEntity(EntityType<? extends WaterAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V", ordinal = 0))
    private void onMoveRandomGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (squidHasMoveRandomGoal.get()) instance.addGoal(pPriority, pTask);
    }

    @Redirect(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V", ordinal = 1))
    private void onFleeGoal(GoalSelector instance, int pPriority, Goal pTask) {
        if (squidHasFleeGoal.get()) instance.addGoal(pPriority, pTask);
    }
}
