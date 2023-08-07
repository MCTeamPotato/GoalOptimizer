package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = SheepEntity.class, priority = 10)
public abstract class MixinSheepEntity extends AnimalEntity {
    @Shadow private EatGrassGoal eatBlockGoal;

    protected MixinSheepEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        SheepEntity sheepEntity = (SheepEntity)(Object)this;
        this.eatBlockGoal = new EatGrassGoal(sheepEntity);
        if (sheepHasSwimGoal.get()) this.goalSelector.addGoal(0, new SwimGoal(sheepEntity));
        if (sheepHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(sheepEntity, 1.25D));
        if (sheepHasBreedGoal.get()) this.goalSelector.addGoal(2, new BreedGoal(sheepEntity, 1.0D));
        if (sheepHasTemptGoal.get()) this.goalSelector.addGoal(3, new TemptGoal(sheepEntity, 1.1D, Ingredient.of(Items.WHEAT), false));
        if (sheepHasFollowParentGoal.get()) this.goalSelector.addGoal(4, new FollowParentGoal(sheepEntity, 1.1D));
        if (sheepHasEatGrassGoal.get()) this.goalSelector.addGoal(5, this.eatBlockGoal);
        if (sheepHasWaterAvoidingRandomWalkingGoal.get()) this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(sheepEntity, 1.0D));
        if (sheepHasLookAtGoal.get()) this.goalSelector.addGoal(7, new LookAtGoal(sheepEntity, PlayerEntity.class, 6.0F));
        if (sheepHasLookRandomlyGoal.get()) this.goalSelector.addGoal(8, new LookRandomlyGoal(sheepEntity));
    }
}
