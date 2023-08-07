package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = CowEntity.class, priority = 10)
public abstract class MixinCowEntity extends AnimalEntity {
    protected MixinCowEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        CowEntity cowEntity = (CowEntity) (Object) this;
        if (cowHasSwimGoal.get()) this.goalSelector.addGoal(0, new SwimGoal(cowEntity));
        if (cowHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(cowEntity, 2.0D));
        if (cowHasBreedGoal.get()) this.goalSelector.addGoal(2, new BreedGoal(cowEntity, 1.0D));
        if (cowHasTemptGoal.get()) this.goalSelector.addGoal(3, new TemptGoal(cowEntity, 1.25D, Ingredient.of(Items.WHEAT), false));
        if (cowHasFollowParentGoal.get()) this.goalSelector.addGoal(4, new FollowParentGoal(cowEntity, 1.25D));
        if (cowHasWaterAvoidingRandomWalkingGoal.get()) this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(cowEntity, 1.0D));
        if (cowHasLookAtGoal.get()) this.goalSelector.addGoal(6, new LookAtGoal(cowEntity, PlayerEntity.class, 6.0F));
        if (cowHasLookRandomlyGoal.get()) this.goalSelector.addGoal(7, new LookRandomlyGoal(cowEntity));
    }
}
