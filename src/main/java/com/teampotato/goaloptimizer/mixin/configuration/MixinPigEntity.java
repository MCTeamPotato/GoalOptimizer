package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = PigEntity.class, priority = 10)
public abstract class MixinPigEntity extends AnimalEntity {
    @Shadow @Final private static Ingredient FOOD_ITEMS;

    protected MixinPigEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        PigEntity pigEntity = (PigEntity) (Object) this;
        if (pigHasSwimGoal.get()) this.goalSelector.addGoal(0, new SwimGoal(pigEntity));
        if (pigHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(pigEntity, 1.25D));
        if (pigHasBreedGoal.get()) this.goalSelector.addGoal(3, new BreedGoal(pigEntity, 1.0D));
        if (pigHasTemptGoalOfCarrotOnAStick.get()) this.goalSelector.addGoal(4, new TemptGoal(pigEntity, 1.2D, Ingredient.of(Items.CARROT_ON_A_STICK), false));
        if (pigHasTemptGoalOnFood.get()) this.goalSelector.addGoal(4, new TemptGoal(pigEntity, 1.2D, false, FOOD_ITEMS));
        if (pigHasFollowParentGoal.get()) this.goalSelector.addGoal(5, new FollowParentGoal(pigEntity, 1.1D));
        if (pigHasWaterAvoidingRandomWalingGoal.get()) this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(pigEntity, 1.0D));
        if (pigHasLookAtGoal.get()) this.goalSelector.addGoal(7, new LookAtGoal(pigEntity, PlayerEntity.class, 6.0F));
        if (pigHasLookRandomlyGoal.get()) this.goalSelector.addGoal(8, new LookRandomlyGoal(pigEntity));
    }
}
