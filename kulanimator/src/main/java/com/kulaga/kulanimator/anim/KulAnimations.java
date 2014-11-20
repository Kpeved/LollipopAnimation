package com.kulaga.kulanimator.anim;

import com.kulaga.kulanimator.anim.animators.HideFabAnimator;
import com.kulaga.kulanimator.anim.animators.HideFab2Animator;
import com.kulaga.kulanimator.anim.animators.HideLinearRightAnimator;
import com.kulaga.kulanimator.anim.animators.ShowFabAnimator;

/**
 * Created by Michail Kulaga on 11/18/2014.
 */
public enum KulAnimations  {

    HideFabAnimator(HideFabAnimator.class),
    HideFab2Animator(HideFab2Animator.class),
    ShowFabAnimator(ShowFabAnimator.class),
    HideLinearRightAnimator(HideLinearRightAnimator.class);


    private Class mClass;

    private KulAnimations(Class mClass) {
        this.mClass = mClass;
    }

    public BaseAnimator getAnimator() {
        try {
            return (BaseAnimator) mClass.newInstance();
        } catch (Exception e) {
            throw new Error("Can not init mClass instance");
        }
    }

}
