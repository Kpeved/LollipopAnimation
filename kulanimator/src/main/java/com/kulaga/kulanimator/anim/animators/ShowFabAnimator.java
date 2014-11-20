package com.kulaga.kulanimator.anim.animators;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.kulaga.kulanimator.anim.BaseAnimator;

/**
 * Created by Michail Kulaga on 11/19/2014.
 */
public class ShowFabAnimator extends BaseAnimator {

    @Override
    protected void prepare(View target) {

        ObjectAnimator animationX = ObjectAnimator.ofFloat(target, "translationX", dpToPx(-10), 0);
        ObjectAnimator shrinkY = ObjectAnimator.ofFloat(target, "scaleY", 0.7f, 1.0f);
        ObjectAnimator shrinkX = ObjectAnimator.ofFloat(target, "scaleX", 1.0f, 1.0f);
        ObjectAnimator animationY = ObjectAnimator.ofFloat(target, "translationY",dpToPx(-120),0);
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();
        animationY.setInterpolator(decelerateInterpolator);
        animationX.setInterpolator(decelerateInterpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animationX, animationY, shrinkY,shrinkX);

        getAnimatorAgent().playSequentially(
                animatorSet
        );
    }
}
