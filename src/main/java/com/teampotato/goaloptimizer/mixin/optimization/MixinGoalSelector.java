package com.teampotato.goaloptimizer.mixin.optimization;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Mixin(GoalSelector.class)
public abstract class MixinGoalSelector {
    @Mutable @Shadow @Final private Set<WrappedGoal> availableGoals;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(Supplier<ProfilerFiller> iProfilerSupplier, CallbackInfo ci) {
        this.availableGoals = new ObjectLinkedOpenHashSet<>(this.availableGoals);
    }

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    public void removeGoal(Goal pTask) {
        Iterator<WrappedGoal> goalIterator = this.availableGoals.iterator();
        while (goalIterator.hasNext()) {
            WrappedGoal goal = goalIterator.next();
            if (goal.getGoal() == pTask) {
                if (goal.isRunning()) goal.stop();
                goalIterator.remove();
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
