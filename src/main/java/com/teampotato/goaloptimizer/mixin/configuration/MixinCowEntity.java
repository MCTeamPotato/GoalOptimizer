package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = Cow.class, priority = 10)
public abstract class MixinCowEntity extends Animal {
    protected MixinCowEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        Cow cowEntity = (Cow) (Object) this;
        if (cowHasSwimGoal.get()) this.goalSelector.addGoal(0, new FloatGoal(cowEntity));
        if (cowHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(cowEntity, 2.0D));
        if (cowHasBreedGoal.get()) this.goalSelector.addGoal(2, new BreedGoal(cowEntity, 1.0D));
        if (cowHasTemptGoal.get()) this.goalSelector.addGoal(3, new TemptGoal(cowEntity, 1.25D, Ingredient.of(Items.WHEAT), false));
        if (cowHasFollowParentGoal.get()) this.goalSelector.addGoal(4, new FollowParentGoal(cowEntity, 1.25D));
        if (cowHasWaterAvoidingRandomWalkingGoal.get()) this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(cowEntity, 1.0D));
        if (cowHasLookAtGoal.get()) this.goalSelector.addGoal(6, new LookAtPlayerGoal(cowEntity, Player.class, 6.0F));
        if (cowHasLookRandomlyGoal.get()) this.goalSelector.addGoal(7, new RandomLookAroundGoal(cowEntity));
    }
}
