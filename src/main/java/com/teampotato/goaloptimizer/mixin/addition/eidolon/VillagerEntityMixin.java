package com.teampotato.goaloptimizer.mixin.addition.eidolon;

import elucent.eidolon.Registry;
import elucent.eidolon.entity.ai.PriestBarterGoal;
import elucent.eidolon.item.CodexItem;
import elucent.eidolon.spell.Signs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = VillagerEntity.class, priority = 10)
public abstract class VillagerEntityMixin extends AbstractVillagerEntity {
    public VillagerEntityMixin(EntityType<? extends AbstractVillagerEntity> p_i50185_1_, World p_i50185_2_) {
        super(p_i50185_1_, p_i50185_2_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new PriestBarterGoal((VillagerEntity)(Object) this, (stack) -> stack.getItem() == Registry.CODEX.get(), (stack) -> CodexItem.withSign(stack, Signs.SACRED_SIGN)));
    }
}
