package com.teampotato.goaloptimizer.mixin.configuration.fish;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowSchoolLeaderGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.teampotato.goaloptimizer.GoalOptimizer.abstractGroupFishHasFollowSchoolLeaderGoal;

@Mixin(value = AbstractGroupFishEntity.class, priority = 10)
public abstract class MixinAbstractGroupFishEntity extends AbstractFishEntity {
    public MixinAbstractGroupFishEntity(EntityType<? extends AbstractFishEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        super.registerGoals();
        if(abstractGroupFishHasFollowSchoolLeaderGoal.get()) this.goalSelector.addGoal(5, new FollowSchoolLeaderGoal((AbstractGroupFishEntity) (Object)this));
    }
}
