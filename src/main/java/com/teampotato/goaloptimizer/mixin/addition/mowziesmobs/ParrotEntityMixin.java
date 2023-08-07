package com.teampotato.goaloptimizer.mixin.addition.mowziesmobs;

import com.bobmowzie.mowziesmobs.server.entity.foliaath.EntityFoliaath;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.passive.ShoulderRidingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParrotEntity.class)
public abstract class ParrotEntityMixin extends ShoulderRidingEntity {
    protected ParrotEntityMixin(EntityType<? extends ShoulderRidingEntity> p_i48566_1_, World p_i48566_2_) {
        super(p_i48566_1_, p_i48566_2_);
    }

    @Inject(method = "registerGoals", at = @At("RETURN"))
    private void onRegisterGoal(CallbackInfo ci) {
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>((ParrotEntity)(Object)this, EntityFoliaath.class, 6.0F, 1.0, 1.2));
    }
}
