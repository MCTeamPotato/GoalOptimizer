package com.teampotato.goaloptimizer.mixin.optimization;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Mixin(GoalSelector.class)
public abstract class MixinGoalSelector {
    @Shadow @Final private Set<PrioritizedGoal> availableGoals;
    @Shadow @Final private Map<Goal.Flag, PrioritizedGoal> lockedFlags;
    @Shadow @Final private EnumSet<Goal.Flag> disabledFlags;
    @Shadow @Final private static PrioritizedGoal NO_GOAL;

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    public Stream<PrioritizedGoal> getRunningGoals() {
        List<PrioritizedGoal> runningGoals = new ArrayList<>();
        for (PrioritizedGoal goal : this.availableGoals) {
            if (goal.isRunning()) runningGoals.add(goal);
        }
        return runningGoals.stream();
    }

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

    private boolean anyMatch(PrioritizedGoal goal) {
        for (Goal.Flag flag : goal.getFlags()) {
            if (this.disabledFlags.contains(flag)) return true;
        }
        return false;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V", ordinal = 0))
    private void onGoalStop(Stream<?> instance, Consumer<?> consumer) {
        for (PrioritizedGoal goal : this.availableGoals) {
            if (goal.isRunning() && (anyMatch(goal) || !goal.canContinueToUse())) goal.stop();
        }
    }

    private boolean noneMatch(PrioritizedGoal goal) {
        for (Goal.Flag flag : goal.getFlags()) {
            if (this.disabledFlags.contains(flag)) return false;
        }
        return true;
    }

    private boolean allMatch(PrioritizedGoal goal) {
        for (Goal.Flag flag : goal.getFlags()) {
            if (!this.lockedFlags.getOrDefault(flag, NO_GOAL).canBeReplacedBy(goal)) return false;
        }
        return true;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V", ordinal = 1))
    private void onGoalUpdate(Stream<?> instance, Consumer<?> consumer) {
        for (PrioritizedGoal goal : this.availableGoals) {
            if (!goal.isRunning() && noneMatch(goal) && allMatch(goal) && goal.canUse()) {
                for (Goal.Flag flag : goal.getFlags()) {
                    this.lockedFlags.getOrDefault(flag, NO_GOAL).stop();
                    this.lockedFlags.put(flag, goal);
                }
                goal.start();
            }
        }
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V", ordinal = 2))
    private void onGoalTick(Stream<?> instance, Consumer<?> consumer) {
        for (PrioritizedGoal goal : this.availableGoals) {
            if (goal.isRunning()) goal.tick();
        }
    }
}
