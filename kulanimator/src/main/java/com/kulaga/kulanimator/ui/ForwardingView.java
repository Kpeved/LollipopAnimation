package com.kulaga.kulanimator.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Michail Kulaga on 11/19/2014.
 */
public class ForwardingView extends View {

    protected final View mView;

    public ForwardingView(Context context, View view) {
        super(context);
        mView = view;
    }

    public View getView(){
        return mView;
    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        mView.setOnClickListener(l);
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        mView.setOnLongClickListener(l);
    }

    @Override
    public void setBackground(Drawable background) {
        mView.setBackground(background);
    }

    //TODO: Forward here another listeners
}
