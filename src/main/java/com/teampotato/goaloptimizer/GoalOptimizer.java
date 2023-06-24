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

    public static final ForgeConfigSpec configSpec;

    //Pig
    public static final ForgeConfigSpec.BooleanValue pigHasSwimGoal;
    public static final ForgeConfigSpec.BooleanValue pigHasPanicGoal;
    public static final ForgeConfigSpec.BooleanValue pigHasBreedGoal;
    public static final ForgeConfigSpec.BooleanValue pigHasTemptGoalOfCarrotOnAStick;
    public static final ForgeConfigSpec.BooleanValue pigHasTemptGoalOnFood;
    public static final ForgeConfigSpec.BooleanValue pigHasFollowParentGoal;
    public static final ForgeConfigSpec.BooleanValue pigHasWaterAvoidingRandomWalingGoal;
    public static final ForgeConfigSpec.BooleanValue pigHasLookAtGoal;
    public static final ForgeConfigSpec.BooleanValue pigHasLookRandomlyGoal;

    //Cow
    public static final ForgeConfigSpec.BooleanValue cowHasSwimGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasPanicGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasBreedGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasTemptGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasFollowParentGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasWaterAvoidingRandomWalkingGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasLookAtGoal;
    public static final ForgeConfigSpec.BooleanValue cowHasLookRandomlyGoal;

    //Sheep
    public static final ForgeConfigSpec.BooleanValue sheepHasEatGrassGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasSwimGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasPanicGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasBreedGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasTemptGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasFollowParentGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasWaterAvoidingRandomWalkingGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasLookAtGoal;
    public static final ForgeConfigSpec.BooleanValue sheepHasLookRandomlyGoal;

    //Fish
    public static final ForgeConfigSpec.BooleanValue abstractFishHasPanicGoal;
    public static final ForgeConfigSpec.BooleanValue abstractFishHasAvoidEntityGoal;
    public static final ForgeConfigSpec.BooleanValue abstractFishHasSwimGoal;
    public static final ForgeConfigSpec.BooleanValue abstractGroupFishHasFollowSchoolLeaderGoal;
    public static final ForgeConfigSpec.BooleanValue pufferfishHasPufferGoal;
    public static final ForgeConfigSpec.BooleanValue squidHasMoveRandomGoal;
    public static final ForgeConfigSpec.BooleanValue squidHasFleeGoal;

    //Chicken
    public static final ForgeConfigSpec.BooleanValue chickenHasSwimGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasPanicGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasBreedGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasTemptGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasFollowParentGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasWaterAvoidingRandomWalkingGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasLookAtGoal;
    public static final ForgeConfigSpec.BooleanValue chickenHasLookRandomlyGoal;

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