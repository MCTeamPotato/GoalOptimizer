package com.teampotato.goaloptimizer.mixin.addition.mowziesmobs;

import com.bobmowzie.mowziesmobs.server.ai.AvoidEntityIfNotTamedGoal;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarako;
import com.bobmowzie.mowziesmobs.server.entity.barakoa.EntityBarakoa;
import com.bobmowzie.mowziesmobs.server.entity.foliaath.EntityFoliaath;
import com.bobmowzie.mowziesmobs.server.entity.frostmaw.EntityFrostmaw;
import com.bobmowzie.mowziesmobs.server.entity.naga.EntityNaga;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends AgeableEntity {
    protected AnimalEntityMixin(EntityType<? extends AgeableEntity> p_i48581_1_, World p_i48581_2_) {
        super(p_i48581_1_, p_i48581_2_);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(3, new AvoidEntityIfNotTamedGoal<>((AnimalEntity)(Object)this, EntityFoliaath.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityIfNotTamedGoal<>((AnimalEntity)(Object)this, EntityBarakoa.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityIfNotTamedGoal<>((AnimalEntity)(Object)this, EntityBarako.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityIfNotTamedGoal<>((AnimalEntity)(Object)this, EntityNaga.class, 10.0F, 1.0, 1.2));
        this.goalSelector.addGoal(3, new AvoidEntityIfNotTamedGoal<>((AnimalEntity)(Object)this, EntityFrostmaw.class, 10.0F, 1.0, 1.2));
    }
}
