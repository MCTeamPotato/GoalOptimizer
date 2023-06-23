package com.teampotato.goaloptimizer.mixin.configuration;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static com.teampotato.goaloptimizer.GoalOptimizer.*;

@Mixin(value = Sheep.class, priority = 10)
public abstract class MixinSheepEntity extends Animal {
    @Shadow private EatBlockGoal eatBlockGoal;

    protected MixinSheepEntity(EntityType<? extends Animal> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * @author Kasualix
     * @reason impl goal remover
     */
    @Overwrite
    protected void registerGoals() {
        Sheep sheepEntity = (Sheep)(Object)this;
        this.eatBlockGoal = new EatBlockGoal(sheepEntity);
        if (sheepHasSwimGoal.get()) this.goalSelector.addGoal(0, new FloatGoal(sheepEntity));
        if (sheepHasPanicGoal.get()) this.goalSelector.addGoal(1, new PanicGoal(sheepEntity, 1.25D));
        if (sheepHasBreedGoal.get()) this.goalSelector.addGoal(2, new BreedGoal(sheepEntity, 1.0D));
        if (sheepHasTemptGoal.get()) this.goalSelector.addGoal(3, new TemptGoal(sheepEntity, 1.1D, Ingredient.of(Items.WHEAT), false));
        if (sheepHasFollowParentGoal.get()) this.goalSelector.addGoal(4, new FollowParentGoal(sheepEntity, 1.1D));
        if (sheepHasEatGrassGoal.get()) this.goalSelector.addGoal(5, this.eatBlockGoal);
        if (sheepHasWaterAvoidingRandomWalkingGoal.get()) this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(sheepEntity, 1.0D));
        if (sheepHasLookAtGoal.get()) this.goalSelector.addGoal(7, new LookAtPlayerGoal(sheepEntity, Player.class, 6.0F));
        if (sheepHasLookRandomlyGoal.get()) this.goalSelector.addGoal(8, new RandomLookAroundGoal(sheepEntity));
    }
}
