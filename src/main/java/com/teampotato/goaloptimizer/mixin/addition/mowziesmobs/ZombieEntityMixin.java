package com.teampotato.goaloptimizer.mixin.addition.mowziesmobs;

import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarako;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarakoa;
import com.bobmowzie.mowziesmobs.server.entity.foliaath.EntityFoliaath;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends MonsterEntity {
    protected ZombieEntityMixin(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    @Inject(method = "registerGoals", at = @At("RETURN"))
    private void onRegisterGoal(CallbackInfo ci) {
        if (((ZombieEntity)(Object) this) instanceof ZombifiedPiglinEntity) return;
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>((ZombieEntity)(Object)this, EntityFoliaath.class, 0, true, false, null));
        this.goalSelector.addGoal(3, new NearestAttackableTargetGoal<>((ZombieEntity)(Object)this, EntityBarakoa.class, 0, true, false, null));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>((ZombieEntity)(Object)this, EntityBarako.class, 0, true, false, null));
    }
}
