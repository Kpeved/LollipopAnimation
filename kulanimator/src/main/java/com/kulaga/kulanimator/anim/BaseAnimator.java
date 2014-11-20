package com.kulaga.kulanimator.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;


/**
 * Created by Michail Kulaga on 11/18/2014.
 */
public abstract class BaseAnimator  {

    public static final long DURATION = 200;

    protected Context mContext;

    private AnimatorSet mAnimatorSet ;
    private long mDuration = DURATION;

    {
        mAnimatorSet = new AnimatorSet();
    }

    protected abstract void prepare(View target);

    public void animate(View target) {
        prepare(target);
        start();
    }


    protected void start() {
        mAnimatorSet.setDuration(mDuration);
        mAnimatorSet.start();

    }

    public BaseAnimator setDuration(long duration) {
        mDuration = duration;
        return this;
    }

    public BaseAnimator setStartDelay(long delay) {
        getAnimatorAgent().setStartDelay(delay);
        return this;
    }

    public long getStartDelay() {
        return mAnimatorSet.getStartDelay();
    }

    public BaseAnimator addAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.addListener(l);
        return this;
    }

    public void cancel(){
        mAnimatorSet.cancel();
    }

    public boolean isRunning(){
        return mAnimatorSet.isRunning();
    }

    public boolean isStarted(){
        return mAnimatorSet.isStarted();
    }

    public void removeAnimatorListener(Animator.AnimatorListener l) {
        mAnimatorSet.removeListener(l);
    }

    public void removeAllListener() {
        mAnimatorSet.removeAllListeners();
    }

    public BaseAnimator setInterpolator(Interpolator interpolator) {
        mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public long getDuration() {
        return mDuration;
    }

    public AnimatorSet getAnimatorAgent() {
        return mAnimatorSet;
    }


    protected int dpToPx(int dp) {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public BaseAnimator setContext(Context context) {
        mContext = context;
        return this;
    }
}
