package com.kulaga.kulanimator.anim.animators;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.kulaga.kulanimator.anim.BaseAnimator;

/**
 * Created by Michail Kulaga on 11/18/2014.
 */
public class HideFabAnimator extends BaseAnimator{

    @Override
    protected void prepare(View target) {

        ObjectAnimator animationX = ObjectAnimator.ofFloat(target, "translationX", 0, dpToPx(-20));
        ObjectAnimator shrinkY = ObjectAnimator.ofFloat(target, "scaleY", 1.0f, 0.0f);
        ObjectAnimator shrinkX = ObjectAnimator.ofFloat(target, "scaleX", 1.0f, 0.9f);
        ObjectAnimator animationY = ObjectAnimator.ofFloat(target, "translationY",0,dpToPx(90));
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
        animationX.setInterpolator(accelerateInterpolator);
        shrinkY.setInterpolator(accelerateInterpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animationX, animationY, shrinkY,shrinkX);

        getAnimatorAgent().playSequentially(
                animatorSet
        );

    }
}
