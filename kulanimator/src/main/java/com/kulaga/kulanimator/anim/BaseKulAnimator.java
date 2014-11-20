package com.kulaga.kulanimator.anim;

import android.animation.Animator;
import android.content.Context;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michail Kulaga on 11/20/2014.
 */
public class BaseKulAnimator {

    private static final long DURATION = BaseAnimator.DURATION;
    private static final long NO_DELAY = 0;

    private Context mContext;

    private KulAnimations mKulAnimation;
    // Main view
    private View mAnimatedView;
    // View behind main view (for example toolbar) to hide it behind behindView
    private View mBehindView;
    //Delay for starting animation
    private long mDelay;
    // Duration of animation
    private long mDuration;
    // Standart interpolator
    private Interpolator mInterpolator;
    // Listener for animation callbacks
    private List<Animator.AnimatorListener> mListenerList;

    BaseKulAnimator(AnimatorBuilder animatorBuilder) {
        mContext = animatorBuilder.mmContext;
        mKulAnimation = animatorBuilder.mmKulAnimation;
        mAnimatedView = animatorBuilder.mmAnimatedView;
        mBehindView = animatorBuilder.mmBehindView;
        mDelay = animatorBuilder.mmDelay;
        mDuration = animatorBuilder.mmDuration;
        mInterpolator = animatorBuilder.mmInterpolator;
        mListenerList = animatorBuilder.mmListenerList;
    }


    public static AnimatorBuilder with(Context context,KulAnimations kulAnimations) {
        return new AnimatorBuilder(context, kulAnimations);
    }

    public static final class AnimatorBuilder {


        private Context mmContext;
        private KulAnimations mmKulAnimation;
        private View mmAnimatedView;
        private View mmBehindView;
        private long mmDelay = NO_DELAY;
        private long mmDuration = DURATION;
        private Interpolator mmInterpolator;
        private List<Animator.AnimatorListener> mmListenerList = new ArrayList<Animator.AnimatorListener>();



        private AnimatorBuilder(Context context,KulAnimations kulAnimation) {
            this.mmKulAnimation = kulAnimation;
            this.mmContext = context;
        }

        public AnimatorBuilder behind(View behindView) {
            this.mmBehindView = behindView;
            return this;
        }

        public AnimatorBuilder delay(long delay) {
            this.mmDelay = delay;
            return this;
        }

        public AnimatorBuilder duration(long duration) {
            this.mmDuration = duration;
            return this;
        }

        public AnimatorBuilder interpolate(Interpolator interpolator) {
            this.mmInterpolator = interpolator;
            return this;
        }

        public AnimatorBuilder addListener(Animator.AnimatorListener listener) {
            mmListenerList.add(listener);
            return this;
        }

        public void start(View target) {
            this.mmAnimatedView = target;
            new BaseKulAnimator(this).startAnimation();
        }

    }

    private BaseAnimator startAnimation() {
        final BaseAnimator animator = mKulAnimation.getAnimator();

        animator.setContext(mContext)
                .setDuration(mDuration)
                .setInterpolator(mInterpolator)
                .setStartDelay(mDelay);

        if (mListenerList.size() > 0) {
            for (Animator.AnimatorListener listener : mListenerList) {
                animator.addAnimatorListener(listener);
            }
        }


        animator.animate(mAnimatedView);
        return animator;
    }


}
