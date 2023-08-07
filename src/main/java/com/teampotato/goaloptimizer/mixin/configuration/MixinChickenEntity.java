package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = ChickenEntity.class, priority = 10)
public abstract class MixinChickenEntity extends AnimalEntity {
    @Shadow @Final private static Ingredient FOOD_ITEMS;
    protected MixinChickenEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        ChickenEntity chickenEntity = (ChickenEntity) (Object) this;
        if (chickenHasSwimGoal.get()) this.goalSelector.addGoal(0, new SwimGoal(chickenEntity));
        if (chickenHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(chickenEntity, 1.4D));
        if (chickenHasBreedGoal.get()) this.goalSelector.addGoal(2, new BreedGoal(chickenEntity, 1.0D));
        if (chickenHasTemptGoal.get()) this.goalSelector.addGoal(3, new TemptGoal(chickenEntity, 1.0D, false, FOOD_ITEMS));
        if (chickenHasFollowParentGoal.get()) this.goalSelector.addGoal(4, new FollowParentGoal(chickenEntity, 1.1D));
        if (chickenHasWaterAvoidingRandomWalkingGoal.get()) this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(chickenEntity, 1.0D));
        if (chickenHasLookAtGoal.get()) this.goalSelector.addGoal(6, new LookAtGoal(chickenEntity, PlayerEntity.class, 6.0F));
        if (chickenHasLookRandomlyGoal.get()) this.goalSelector.addGoal(7, new LookRandomlyGoal(chickenEntity));
    }
}