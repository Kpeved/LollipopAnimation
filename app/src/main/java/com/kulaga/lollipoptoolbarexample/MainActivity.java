package com.kulaga.lollipoptoolbarexample;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kulaga.kulanimator.anim.KulAnimator;
import com.kulaga.kulanimator.ui.LpActionButton;


public class MainActivity extends ActionBarActivity {

    private Fragment mCurrentFragment;

    private ColorDrawable mActionBarBackground;
    private LpActionButton mLpActionButton;

    private int mCageCounter;
    private boolean isToggled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBarBackground = new ColorDrawable(getResources().getColor(R.color.dark_green));
        getSupportActionBar().setBackgroundDrawable(mActionBarBackground);

        mLpActionButton = LpActionButton.createLpActionButton(this,R.layout.button_my_lp);

        mLpActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isToggled){
                    mLpActionButton.setSecondDrawable(R.drawable.button_lp);
                } else {
                    mLpActionButton.setSecondDrawable(R.drawable.button_lp2);
                }
                checkCage();
                mLpActionButton.hide();
            }
        });
        mLpActionButton.setOnShowHideListener(new LpActionButton.OnShowHideListener() {
            @Override
            public void onAnimationFinished(boolean isVisible) {
                if (!isVisible) {
                    isToggled = !isToggled;
                    if(isToggled) {
                        final int colorFrom = mActionBarBackground.getColor();
                        final int colorTo = getResources().getColor(R.color.purple);
                        changeActionBarColor(colorFrom,colorTo);
                    } else {
                        final int colorFrom = mActionBarBackground.getColor();
                        final int colorTo = getResources().getColor(R.color.dark_green);
                        changeActionBarColor(colorFrom, colorTo);
                    }

                    mLpActionButton.show();

                }

            }

            @Override
            public void onAnimationStarted(boolean isShowing) {

            }
        });

    }
/*
    private <T extends BaseFragment> void  setFragment(BaseFragment fragment, boolean shoudAddToBackStack){

        if(fragment == null)
            return;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_place, fragment, fragment.getTagName());

        if(shoudAddToBackStack){
            transaction.addToBackStack(fragment.getTagName());
        }
        transaction.commit();

        mCurrentFragment = fragment;


    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeActionBarColor(int fromColor, int toColor){
        changeActionBarColor(fromColor,toColor,KulAnimator.DEF_DURATION);
    }

    public void changeActionBarColor(int fromColor, int toColor, long duration){
        KulAnimator.changeColorDrawableAnimation(fromColor, toColor, duration, mActionBarBackground).start();
    }

    private void checkCage() {
        if(mCageCounter==4){
            mLpActionButton.setSecondDrawable(R.drawable.nick_cage);
            mCageCounter = 0;
        } else mCageCounter ++ ;
    }

}
