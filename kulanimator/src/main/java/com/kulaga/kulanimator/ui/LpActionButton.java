package com.kulaga.kulanimator.ui;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.kulaga.kulanimator.R;
import com.kulaga.kulanimator.anim.BaseKulAnimator;
import com.kulaga.kulanimator.anim.KulAnimations;
import com.kulaga.kulanimator.anim.KulAnimator;
import com.kulaga.kulanimator.utils.ActionBarUtils;

/**
 * Created by Michail Kulaga on 11/19/2014.
 */
public class LpActionButton extends ForwardingView implements Animator.AnimatorListener {

    /**
     * Listener for show/hide events
     */
    public interface OnShowHideListener{
        public void onAnimationFinished(boolean isVisible);
        public void onAnimationStarted(boolean isVisible);
    }

    public void setOnShowHideListener(OnShowHideListener onShowHideListener){
        mOnShowHideListener = onShowHideListener;
    }

    private OnShowHideListener mOnShowHideListener;

    private Activity mActivity;


    private View mActionContainer;
    private ViewGroup mImageButtonContainer;

    private boolean mIsVisible;
    private boolean mShouldBlend;

    private Drawable mFadingDrawable;
    private Drawable mBackgroundDrawable;

    private BaseKulAnimator.AnimatorBuilder mHideAnimator;
    private BaseKulAnimator.AnimatorBuilder mShowAnimator;



    /**
     *  Creates Button above ActionButton
     * @param activity - current activity
     * @param layoutId - layout of ViewGroup with one element
     * @throws com.kulaga.kulanimator.exception.NoActionBarException if we don't have actionBar
     */
    public static LpActionButton createLpActionButton(Activity activity, int layoutId){
        ViewGroup viewGroup = loadFromRes(activity, layoutId);
        return createLpActionButton(activity, viewGroup);
    }

    /**
     *  Creates Button above ActionButton
     * @param activity - current activity
     * @param viewGroup - inflated ViewGroup with one element
     * @throws com.kulaga.kulanimator.exception.NoActionBarException if we don't have actionBar
     */
    public static LpActionButton createLpActionButton(Activity activity, ViewGroup viewGroup){
        View view = viewGroup.getChildAt(0);
        return new LpActionButton(activity,viewGroup,view);
    }

    private static ViewGroup loadFromRes(Context context, int layoutId) {
        return (ViewGroup) LayoutInflater.from(context).inflate(layoutId,null);
    }

    private LpActionButton(Activity activity, ViewGroup viewGroup , View view ){
        super(activity,view);
        mActivity = activity;
        initButton(viewGroup);
    }


    private void initButton(ViewGroup viewGroup) {

        mImageButtonContainer = viewGroup;

        ViewGroup vg = getMainContainer();
        mActionContainer = vg.getChildAt(0);
        vg.addView(mImageButtonContainer);

        if(mView.getVisibility()== VISIBLE){
            mIsVisible = true;
        } else {
            mIsVisible = false;
        }

        mFadingDrawable = mActivity.getResources().getDrawable(R.drawable.shade_shape);
        mBackgroundDrawable = mView.getBackground();


        ActionBarUtils.setActionBarMarginToView(mView, ActionBarUtils.getActionBarHeight(mActivity));

        initAnimations();
    }

    private ViewGroup getMainContainer() {

        ActionBarUtils.checkForActionBar(mActivity);

        // Retreiving our ActionBarContainer for placing our button. This is kostyl, but, heh..
        Window window = mActivity.getWindow();
        ViewGroup v = (ViewGroup) window.getDecorView();
        // propably we have the sae hierarchy everywere, so, why not
        return (ViewGroup)((ViewGroup) v.getChildAt(0)).getChildAt(1);
    }



    public void setShouldBlend(boolean shouldBlend) {
        mShouldBlend = shouldBlend;
    }

    public void hide(){
        if(mView.getVisibility() != View.VISIBLE){
            return;
        }
        mHideAnimator.start(mView);
    }

    private void transitionAnimation() {
        final Drawable background = mView.getBackground();
        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.ic_blur);
        KulAnimator.transitionDrawableAnimation(mView,background,drawable).startTransition(200);
    }



    public void show(){
        if(mView.getVisibility() == View.VISIBLE){
            return;
        }
        mView.setVisibility(View.VISIBLE);

        mShowAnimator.start(mView);

    }

    public void toggle(){
        if(mIsVisible){
            hide();
        } else {
            show();
        }
    }


    private void initAnimations(){
        initHideAnimation();
        initShowAnimation();
    }

    private void initHideAnimation() {



        // First animation
        mHideAnimator = KulAnimator.with(mActivity,KulAnimations.HideFabAnimator).behind(mActionContainer).addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (mOnShowHideListener != null) {
                    mOnShowHideListener.onAnimationStarted(mIsVisible);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                bringToFrontActionBar();




                if (mShouldBlend) {
                    transitionAnimation();
                }

                // starting second animation behind action bar
                KulAnimator.with(mActivity,KulAnimations.HideFab2Animator).behind(mActionContainer).addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mIsVisible = false;
                        mView.setVisibility(View.INVISIBLE);
                        if (mOnShowHideListener != null) {
                            mOnShowHideListener.onAnimationFinished(mIsVisible);
                        }
                        mView.setBackground(mFadingDrawable);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                }).duration(100).start(mView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).duration(250);
    }


    private void initShowAnimation() {
        mShowAnimator = KulAnimator.with(mActivity,KulAnimations.ShowFabAnimator).addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                bringToFrontButton();

                if (mOnShowHideListener != null) {
                    mOnShowHideListener.onAnimationStarted(mIsVisible);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mIsVisible = true;
                if (mOnShowHideListener != null) {
                    mOnShowHideListener.onAnimationFinished(mIsVisible);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        }).duration(200);
    }

    private void bringToFrontActionBar(){

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup container = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
            container.getOverlay().add(mView);
        } else {
            mActionContainer.bringToFront();
            mActionContainer.requestLayout();
        }
    }

    private void bringToFrontButton(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup container = (ViewGroup) mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
            container.getOverlay().remove(mView);
            mImageButtonContainer.addView(mView);
        } else {
            mImageButtonContainer.bringToFront();
            mImageButtonContainer.requestLayout();
        }
    }

    private void toggleVisibility(){
        if(mIsVisible){
            mIsVisible = false;
            mView.setVisibility(View.INVISIBLE);
        } else {
            mIsVisible = true;
            mView.setVisibility(View.VISIBLE);
        }
    }


    public void setHideAnimator(BaseKulAnimator.AnimatorBuilder animator){
        mHideAnimator = animator;
        mHideAnimator.addListener(this);
    }

    public void setShowAnimator(BaseKulAnimator.AnimatorBuilder animator){
        mShowAnimator = animator;
        mShowAnimator.addListener(this);
    }


    @Override
    public void onAnimationStart(Animator animation) {
        if (mOnShowHideListener != null) {
            mOnShowHideListener.onAnimationStarted(mIsVisible);
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        toggleVisibility();
        if (mOnShowHideListener != null) {
            mOnShowHideListener.onAnimationFinished(mIsVisible);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }



    public void setSecondDrawable(int resourceId){
        setSecondDrawable(mActivity.getResources().getDrawable(resourceId));
    }

    public void setSecondDrawable(Drawable drawable){
        mFadingDrawable = drawable;
        mShouldBlend = true;
    }

}
