package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = Chicken.class, priority = 10)
public abstract class MixinChickenEntity extends Animal {
    @Shadow @Final private static Ingredient FOOD_ITEMS;
    protected MixinChickenEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        Chicken chickenEntity = (Chicken) (Object) this;
        if (chickenHasSwimGoal.get()) this.goalSelector.addGoal(0, new FloatGoal(chickenEntity));
        if (chickenHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(chickenEntity, 1.4D));
        if (chickenHasBreedGoal.get()) this.goalSelector.addGoal(2, new BreedGoal(chickenEntity, 1.0D));
        if (chickenHasTemptGoal.get()) this.goalSelector.addGoal(3, new TemptGoal(chickenEntity, 1.0D, FOOD_ITEMS, false));
        if (chickenHasFollowParentGoal.get()) this.goalSelector.addGoal(4, new FollowParentGoal(chickenEntity, 1.1D));
        if (chickenHasWaterAvoidingRandomWalkingGoal.get()) this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(chickenEntity, 1.0D));
        if (chickenHasLookAtGoal.get()) this.goalSelector.addGoal(6, new LookAtPlayerGoal(chickenEntity, Player.class, 6.0F));
        if (chickenHasLookRandomlyGoal.get()) this.goalSelector.addGoal(7, new RandomLookAroundGoal(chickenEntity));
    }
}