package F.epicfight_dd.gameasset.animation;

import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;

public class WingStanceAnims {


    public static AnimationManager.AnimationAccessor<StaticAnimation> WING_STANCE_IDLE;

    public static AnimationManager.AnimationAccessor<AttackAnimation> WING_STANCE_SKILL;
    public static AnimationManager.AnimationAccessor<AttackAnimation> WING_STANCE_SKILL2;


    public static void build(AnimationManager.AnimationBuilder builder){


        WING_STANCE_IDLE = builder.nextAccessor("biped/skill/wingstance/wingstance_idle",
                accessor -> new StaticAnimation(
                        0.8f,true,accessor, Armatures.BIPED
                ));





    }


}
