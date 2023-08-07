package com.teampotato.goaloptimizer.mixin.addition.eidolon;

import elucent.eidolon.Registry;
import elucent.eidolon.entity.ai.WitchBarterGoal;
import elucent.eidolon.item.CodexItem;
import elucent.eidolon.spell.Signs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitchEntity.class)
public abstract class WitchEntityMixin extends AbstractRaiderEntity {
    protected WitchEntityMixin(EntityType<? extends AbstractRaiderEntity> p_i50143_1_, World p_i50143_2_) {
        super(p_i50143_1_, p_i50143_2_);
    }

    @Inject(method = "registerGoals", at = @At("RETURN"))
    private void onRegisterGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(1, new WitchBarterGoal((WitchEntity)(Object) this, (stack) -> stack.getItem() == Registry.CODEX.get(), (stack) -> CodexItem.withSign(stack, Signs.WICKED_SIGN)));
    }
}
