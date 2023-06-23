package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = Pig.class, priority = 10)
public abstract class MixinPigEntity extends Animal {
    @Shadow @Final private static Ingredient FOOD_ITEMS;

    protected MixinPigEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        Pig pigEntity = (Pig) (Object) this;
        if (pigHasSwimGoal.get()) this.goalSelector.addGoal(0, new FloatGoal(pigEntity));
        if (pigHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(pigEntity, 1.25D));
        if (pigHasBreedGoal.get()) this.goalSelector.addGoal(3, new BreedGoal(pigEntity, 1.0D));
        if (pigHasTemptGoalOfCarrotOnAStick.get()) this.goalSelector.addGoal(4, new TemptGoal(pigEntity, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        if (pigHasTemptGoalOnFood.get()) this.goalSelector.addGoal(4, new TemptGoal(pigEntity, 1.2D, FOOD_ITEMS, false));
        if (pigHasFollowParentGoal.get()) this.goalSelector.addGoal(5, new FollowParentGoal(pigEntity, 1.1D));
        if (pigHasWaterAvoidingRandomWalingGoal.get()) this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(pigEntity, 1.0D));
        if (pigHasLookAtGoal.get()) this.goalSelector.addGoal(7, new LookAtPlayerGoal(pigEntity, Player.class, 6.0F));
        if (pigHasLookRandomlyGoal.get()) this.goalSelector.addGoal(8, new RandomLookAroundGoal(pigEntity));
    }
}
