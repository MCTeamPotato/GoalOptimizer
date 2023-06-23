package com.teampotato.goaloptimizer.mixin.optimization;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;
import java.util.stream.Stream;

@Mixin(GoalSelector.class)
public abstract class MixinGoalSelector {
    @Shadow @Final private Set<WrappedGoal> availableGoals;

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    public void removeGoal(Goal pGoal) {
        for (WrappedGoal goal : this.availableGoals) {
            if (goal.getGoal() == pGoal) {
                this.availableGoals.remove(goal);
                if (goal.isRunning()) goal.stop();
            }
        }
    }

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    public Stream<WrappedGoal> getRunningGoals() {
        List<WrappedGoal> runningGoals = new ArrayList<>();
        for (WrappedGoal goal : this.availableGoals) {
            if (goal.isRunning()) runningGoals.add(goal);
        }
        return runningGoals.stream();
    }
}
