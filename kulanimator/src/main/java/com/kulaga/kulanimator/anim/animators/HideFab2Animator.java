package com.kulaga.kulanimator.anim.animators;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.kulaga.kulanimator.anim.BaseAnimator;

/**
 * Created by Michail Kulaga on 11/19/2014.
 */
public class HideFab2Animator extends BaseAnimator {
    @Override
    protected void prepare(View target) {

        ObjectAnimator animationX = ObjectAnimator.ofFloat(target, "translationX", dpToPx(-20), dpToPx(-40));
        ObjectAnimator shrinkY = ObjectAnimator.ofFloat(target, "scaleY", 0.0f, 1.3f);
        ObjectAnimator shrinkX = ObjectAnimator.ofFloat(target, "scaleX", 0.9f, 0.4f);
        ObjectAnimator animationY = ObjectAnimator.ofFloat(target, "translationY",dpToPx(90),dpToPx(-200));
        animationY.setInterpolator(new AccelerateInterpolator());
        animationX.setInterpolator(new DecelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animationX, animationY, shrinkY, shrinkX);

        getAnimatorAgent().playSequentially(
                animatorSet
        );
    }
}
