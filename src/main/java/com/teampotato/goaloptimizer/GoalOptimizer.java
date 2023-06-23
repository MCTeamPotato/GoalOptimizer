package com.teampotato.goaloptimizer;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Set;

@Mod(GoalOptimizer.ID)
public class GoalOptimizer {
    public static final String ID = "goaloptimizer";

    public GoalOptimizer() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, configSpec);
    }

    public static ForgeConfigSpec configSpec;

    //Pig
    public static ForgeConfigSpec.BooleanValue
            pigHasSwimGoal,
            pigHasPanicGoal,
            pigHasBreedGoal,
            pigHasTemptGoalOfCarrotOnAStick,
            pigHasTemptGoalOnFood,
            pigHasFollowParentGoal,
            pigHasWaterAvoidingRandomWalingGoal,
            pigHasLookAtGoal,
            pigHasLookRandomlyGoal;

    //Cow
    public static ForgeConfigSpec.BooleanValue
            cowHasSwimGoal,
            cowHasPanicGoal,
            cowHasBreedGoal,
            cowHasTemptGoal,
            cowHasFollowParentGoal,
            cowHasWaterAvoidingRandomWalkingGoal,
            cowHasLookAtGoal,
            cowHasLookRandomlyGoal;

    //Sheep
    public static ForgeConfigSpec.BooleanValue
            sheepHasEatGrassGoal,
            sheepHasSwimGoal,
            sheepHasPanicGoal,
            sheepHasBreedGoal,
            sheepHasTemptGoal,
            sheepHasFollowParentGoal,
            sheepHasWaterAvoidingRandomWalkingGoal,
            sheepHasLookAtGoal,
            sheepHasLookRandomlyGoal;

    //Fish
    public static ForgeConfigSpec.BooleanValue
            abstractFishHasPanicGoal,
            abstractFishHasAvoidEntityGoal,
            abstractFishHasSwimGoal,
            abstractGroupFishHasFollowSchoolLeaderGoal,
            pufferfishHasPufferGoal,
            squidHasMoveRandomGoal,
            squidHasFleeGoal;

    //Chicken
    public static ForgeConfigSpec.BooleanValue
            chickenHasSwimGoal,
            chickenHasPanicGoal,
            chickenHasBreedGoal,
            chickenHasTemptGoal,
            chickenHasFollowParentGoal,
            chickenHasWaterAvoidingRandomWalkingGoal,
            chickenHasLookAtGoal,
            chickenHasLookRandomlyGoal;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("GoalOptimizer");
        builder.push("Sheep");
        sheepHasEatGrassGoal = builder.define("sheepHasEatGrassGoal", true);
        sheepHasSwimGoal = builder.define("sheepHasSwimGoal", true);
        sheepHasPanicGoal = builder.define("sheepHasPanicGoal", true);
        sheepHasBreedGoal = builder.define("sheepHasBreedGoal", true);
        sheepHasTemptGoal = builder.define("sheepHasTemptGoal", true);
        sheepHasFollowParentGoal = builder.define("sheepHasFollowParentGoal", true);
        sheepHasWaterAvoidingRandomWalkingGoal = builder.define("sheepHasWaterAvoidingRandomWalkingGoal", true);
        sheepHasLookAtGoal = builder.define("sheepHasLookAtGoal", true);
        sheepHasLookRandomlyGoal = builder.define("sheepHasLookRandomlyGoal", true);
        builder.pop();
        builder.push("Pig");
        pigHasSwimGoal = builder.define("pigHasSwimGoal", true);
        pigHasPanicGoal = builder.define("pigHasPanicGoal", true);
        pigHasBreedGoal = builder.define("pigHasBreedGoal", true);
        pigHasTemptGoalOfCarrotOnAStick = builder.define("pigHasTemptGoalOfCarrotOnAStick", true);
        pigHasTemptGoalOnFood = builder.define("pigHasTemptGoalOnFood", true);
        pigHasFollowParentGoal = builder.define("pigHasFollowParentGoal", true);
        pigHasWaterAvoidingRandomWalingGoal = builder.define("pigHasWaterAvoidingRandomWalingGoal", true);
        pigHasLookAtGoal = builder.define("pigHasLookAtGoal", true);
        pigHasLookRandomlyGoal = builder.define("pigHasLookRandomlyGoal", true);
        builder.pop();
        builder.push("Cow");
        cowHasSwimGoal = builder.define("cowHasSwimGoal", true);
        cowHasPanicGoal = builder.define("cowHasPanicGoal", true);
        cowHasBreedGoal = builder.define("cowHasBreedGoal", true);
        cowHasTemptGoal = builder.define("cowHasTemptGoal", true);
        cowHasFollowParentGoal = builder.define("cowHasFollowParentGoal", true);
        cowHasWaterAvoidingRandomWalkingGoal = builder.define("cowHasWaterAvoidingRandomWalkingGoal", true);
        cowHasLookAtGoal = builder.define("cowHasLookAtGoal", true);
        cowHasLookRandomlyGoal = builder.define("cowHasLookRandomlyGoal", true);
        builder.pop();
        builder.push("Fish");
        abstractFishHasPanicGoal = builder.define("abstractFishHasPanicGoal", true);
        abstractFishHasAvoidEntityGoal = builder.define("abstractFishHasAvoidEntityGoal", true);
        abstractFishHasSwimGoal = builder.define("abstractFishHasSwimGoal", true);
        abstractGroupFishHasFollowSchoolLeaderGoal = builder.define("abstractGroupFishHasFollowSchoolLeaderGoal", true);
        pufferfishHasPufferGoal = builder.define("pufferfishHasPufferGoal", true);
        squidHasMoveRandomGoal = builder.define("squidHasMoveRandomGoal", true);
        squidHasFleeGoal = builder.define("squidHasFleeGoal", true);
        builder.pop();
        builder.push("Chicken");
        chickenHasSwimGoal = builder.define("chickenHasSwimGoal", true);
        chickenHasPanicGoal = builder.define("chickenHasPanicGoal", true);
        chickenHasBreedGoal = builder.define("chickenHasBreedGoal", true);
        chickenHasTemptGoal = builder.define("chickenHasTemptGoal", true);
        chickenHasFollowParentGoal = builder.define("chickenHasFollowParentGoal", true);
        chickenHasWaterAvoidingRandomWalkingGoal = builder.define("chickenHasWaterAvoidingRandomWalkingGoal", true);
        chickenHasLookAtGoal = builder.define("chickenHasLookAtGoal", true);
        chickenHasLookRandomlyGoal = builder.define("chickenHasLookRandomlyGoal", true);
        builder.pop();
        configSpec = builder.build();
    }
}