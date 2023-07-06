package com.teampotato.goaloptimizer.mixin.optimization;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.profiler.IProfiler;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mixin(value = GoalSelector.class, priority = 1)
public abstract class MixinGoalSelector {
    @Mutable @Shadow @Final private Set<PrioritizedGoal> availableGoals;
    @Shadow @Final private Map<Goal.Flag, PrioritizedGoal> lockedFlags;
    @Shadow @Final private EnumSet<Goal.Flag> disabledFlags;
    @Shadow @Final private static PrioritizedGoal NO_GOAL;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Supplier<IProfiler> iProfilerSupplier, CallbackInfo ci) {
        this.availableGoals = new ObjectLinkedOpenHashSet<>(this.availableGoals);
    }

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

    @Unique
    private boolean goalOptimizer$anyMatch(PrioritizedGoal goal) {
        for (Goal.Flag flag : goal.getFlags()) {
            if (this.disabledFlags.contains(flag)) return true;
        }
        return false;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V", ordinal = 0))
    private void onGoalStop(Stream<?> instance, Consumer<?> consumer) {
        for (PrioritizedGoal goal : this.availableGoals) {
            if (goal.isRunning() && (goalOptimizer$anyMatch(goal) || !goal.canContinueToUse())) goal.stop();
        }
    }

    @Unique
    private boolean goalOptimizer$noneMatch(PrioritizedGoal goal) {
        for (Goal.Flag flag : goal.getFlags()) {
            if (this.disabledFlags.contains(flag)) return false;
        }
        return true;
    }

    @Unique
    private boolean goalOptimizer$allMatch(PrioritizedGoal goal) {
        for (Goal.Flag flag : goal.getFlags()) {
            if (!this.lockedFlags.getOrDefault(flag, NO_GOAL).canBeReplacedBy(goal)) return false;
        }
        return true;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V", ordinal = 1))
    private void onGoalUpdate(Stream<?> instance, Consumer<?> consumer) {
        for (PrioritizedGoal goal : this.availableGoals) {
            if (!goal.isRunning() && goalOptimizer$noneMatch(goal) && goalOptimizer$allMatch(goal) && goal.canUse()) {
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

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    public void removeGoal(Goal pTask) {
        Iterator<PrioritizedGoal> goalIterator = this.availableGoals.iterator();
        while (goalIterator.hasNext()) {
            PrioritizedGoal goal = goalIterator.next();
            if (goal.getGoal() == pTask) {
                if (goal.isRunning()) goal.stop();
                goalIterator.remove();
            }
        }
    }
}
