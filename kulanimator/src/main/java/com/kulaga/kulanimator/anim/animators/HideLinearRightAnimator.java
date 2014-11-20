package com.kulaga.kulanimator.anim.animators;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.kulaga.kulanimator.anim.BaseAnimator;

/**
 * Created by Michail Kulaga on 11/20/2014.
 */
public class HideLinearRightAnimator extends BaseAnimator {
    @Override
    protected void prepare(View target) {
        ObjectAnimator animationX = ObjectAnimator.ofFloat(target, "translationX", 0, dpToPx(100));
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
        animationX.setInterpolator(accelerateInterpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animationX);

        getAnimatorAgent().playSequentially(
                animatorSet
        );
    }
}
