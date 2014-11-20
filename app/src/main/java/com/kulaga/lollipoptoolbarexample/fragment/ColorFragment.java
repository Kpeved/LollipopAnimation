package com.kulaga.lollipoptoolbarexample.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.kulaga.lollipoptoolbarexample.R;

/**
 * Created by Michail Kulaga on 11/20/2014.
 */
public class ColorFragment extends BaseFragment {

    private static final String KEY_COLOR = "color";
    private final String TAG = ColorFragment.class.getSimpleName();


    public static ColorFragment newInstance(int color) {
        final Bundle args = new Bundle();
        args.putInt(KEY_COLOR, color);
        final ColorFragment fragment = new ColorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getTagName() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FrameLayout rootView = new FrameLayout(getActivity());
        rootView.setBackgroundColor(getArguments().getInt(KEY_COLOR));
        return rootView;
    }

    public int getColor() {
        return getArguments().getInt(KEY_COLOR);
    }


}
