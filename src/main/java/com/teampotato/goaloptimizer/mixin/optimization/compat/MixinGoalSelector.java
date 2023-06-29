package com.teampotato.goaloptimizer.mixin.optimization.compat;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(GoalSelector.class)
public class MixinGoalSelector {
    @Shadow
    @Final
    private Set<PrioritizedGoal> availableGoals;

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    public void removeGoal(Goal pTask) {
        for (PrioritizedGoal goal : this.availableGoals) {
            if (goal.getGoal() == pTask) {
                this.availableGoals.remove(goal);
                if (goal.isRunning()) goal.stop();
            }
        }
    }
}
