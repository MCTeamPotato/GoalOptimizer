package com.teampotato.goaloptimizer.mixin.optimization;

import net.minecraft.util.ClassInheritanceMultiMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;

@Mixin(ClassInheritanceMultiMap.class)
public abstract class MixinClassInheritanceMultiMap<T> extends AbstractCollection<T> {
    @Shadow @Final private Class<T> baseClass;
    @Shadow @Final private Map<Class<?>, List<T>> byClass;
    @Shadow @Final private List<T> allInstances;

    /**
     * @author Kasualix
     * @reason avoid stream
     */
    @Overwrite
    @SuppressWarnings("unchecked")
    public <S> Collection<S> find(Class<S> sClass) {
        if (!this.baseClass.isAssignableFrom(sClass)) {
            throw new IllegalArgumentException("Don't know how to search for " + sClass);
        } else {
            return (Collection<S>) Collections.unmodifiableCollection(
                    this.byClass.computeIfAbsent(sClass, (clazz) -> {
                        List<T> classes = new ArrayList<>(allInstances.size());
                        for (T obj : allInstances) {
                            if (clazz.isInstance(obj)) classes.add(obj);
                        }
                        return classes;
                    })
            );
        }
    }
}
