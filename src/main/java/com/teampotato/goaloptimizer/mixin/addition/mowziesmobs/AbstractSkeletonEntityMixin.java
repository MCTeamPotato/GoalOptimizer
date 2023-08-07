package com.teampotato.goaloptimizer.mixin.addition.mowziesmobs;

import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarako;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarakoa;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSkeletonEntity.class)
public abstract class AbstractSkeletonEntityMixin extends MonsterEntity {
    protected AbstractSkeletonEntityMixin(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    @Inject(method = "registerGoals", at = @At("RETURN"))
    private void onRegisterGoal(CallbackInfo ci) {
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>((AbstractSkeletonEntity)(Object)this, EntityBarakoa.class, 0, true, false, null));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>((AbstractSkeletonEntity)(Object)this, EntityBarako.class, 0, true, false, null));
    }
}
