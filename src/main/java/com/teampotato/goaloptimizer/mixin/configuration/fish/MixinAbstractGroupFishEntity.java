package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.teampotato.goaloptimizer.GoalOptimizer.abstractGroupFishHasFollowSchoolLeaderGoal;

@Mixin(value = AbstractSchoolingFish.class, priority = 10)
public abstract class MixinAbstractGroupFishEntity extends AbstractFish {
    public MixinAbstractGroupFishEntity(EntityType<? extends AbstractFish> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        super.registerGoals();
        if(abstractGroupFishHasFollowSchoolLeaderGoal.get()) this.goalSelector.addGoal(5, new FollowFlockLeaderGoal((AbstractSchoolingFish) (Object)this));
    }
}
