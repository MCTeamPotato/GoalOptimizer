package com.teampotato.goaloptimizer.mixin.addition.mowziesmobs;

import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarako;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarakoa;
import com.bobmowzie.mowziesmobs.server.entity.frostmaw.EntityFrostmaw;
import com.bobmowzie.mowziesmobs.server.entity.naga.EntityNaga;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = AbstractVillagerEntity.class, priority = 10)
public abstract class AbstractVillagerEntityMixin extends AgeableEntity {
    protected AbstractVillagerEntityMixin(EntityType<? extends AgeableEntity> p_i48581_1_, World p_i48581_2_) {
        super(p_i48581_1_, p_i48581_2_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>((AbstractVillagerEntity)(Object)this, EntityBarakoa.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>((AbstractVillagerEntity)(Object)this, EntityBarako.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>((AbstractVillagerEntity)(Object)this, EntityNaga.class, 10.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>((AbstractVillagerEntity)(Object)this, EntityFrostmaw.class, 10.0F, 1.0, 1.2));
    }
}
