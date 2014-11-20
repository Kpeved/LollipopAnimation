package com.kulaga.kulanimator.anim;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;


/**
 * Created by Michail Kulaga on 11/18/2014.
 * Main Animator class
 */
public class KulAnimator extends BaseKulAnimator {

    public final static long DEF_DURATION = 200;

    private KulAnimator(AnimatorBuilder animatorBuilder) {
        super(animatorBuilder);
    }


    public static TransitionDrawable transitionDrawableAnimation(View view, Drawable... drawables ){
        TransitionDrawable trans = new TransitionDrawable(drawables);
        view.setBackground(trans);
        return trans;
    }


    public static ValueAnimator changeColorDrawableAnimation(final int colorFrom, final int colorTo, long duration, final ColorDrawable colorDrawable){
        ValueAnimator anim = ValueAnimator.ofFloat(1.0f, 0.0f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                colorDrawable.setColor(blendColors(colorFrom, colorTo, (Float) animation.getAnimatedValue()));
            }
        });
        anim.setDuration(duration);
        return anim;
    }

    private static int blendColors(int from, int to, float ratio) {
        final float inverseRation = 1f - ratio;
        final float r = Color.red(from) * ratio + Color.red(to) * inverseRation;
        final float g = Color.green(from) * ratio + Color.green(to) * inverseRation;
        final float b = Color.blue(from) * ratio + Color.blue(to) * inverseRation;
        return Color.rgb((int) r, (int) g, (int) b);
    }


}
