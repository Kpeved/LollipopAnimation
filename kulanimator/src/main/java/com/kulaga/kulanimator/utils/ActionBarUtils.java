package com.kulaga.kulanimator.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;

import com.kulaga.kulanimator.exception.NoActionBarException;

/**
 * Created by Michail Kulaga on 11/20/2014.
 */
public class ActionBarUtils {

    public static int getActionBarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int actionBarSize=(int)styledAttributes.getDimension(0,0);
        styledAttributes.recycle();
        return actionBarSize;
    }

    /**
     * Check for actionBar
     * @param activity
     * @throws com.kulaga.kulanimator.exception.NoActionBarException
     */
    public static void checkForActionBar(Activity activity) throws NoActionBarException{
        // Checking for actionBar support, if no throwing exception
        if(activity instanceof ActionBarActivity){
            if(((ActionBarActivity)activity).getSupportActionBar() == null)
                throw new NoActionBarException("No actionBar found. Please, choose theme with actionBar");
        } else if(activity instanceof Activity){
            if(activity.getActionBar()==null)
                throw new NoActionBarException("No actionBar found. Please, choose theme with actionBar");
        }
    }


    public static void setActionBarMarginToView(View v, int margin) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(p.leftMargin,margin/2,p.rightMargin,p.bottomMargin);
            v.requestLayout();
        }
    }

}
