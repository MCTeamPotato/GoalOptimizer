package com.teampotato.goaloptimizer.mixin.deletion.eidolon;

import elucent.eidolon.Events;
import elucent.eidolon.network.KnowledgeUpdatePacket;
import elucent.eidolon.network.Networking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Events.class)
public abstract class EventsMixin {
    /**
     * @author Kasualix
     * @reason avoid register goal in EntityJoinWorldEvent
     */
    @Overwrite
    @SubscribeEvent
    public void registerCustomAI(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof LivingEntity && !event.getWorld().isClientSide && event.getEntity() instanceof PlayerEntity) {
                Networking.sendTo((PlayerEntity)event.getEntity(), new KnowledgeUpdatePacket((PlayerEntity)event.getEntity(), false));
        }
    }
}
