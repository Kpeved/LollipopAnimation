package com.kulaga.lollipoptoolbarexample;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.kulaga.kulanimator.anim.KulAnimator;
import com.kulaga.kulanimator.ui.LpActionButton;


public class MainActivity extends ActionBarActivity {

    private Fragment mCurrentFragment;

    private ColorDrawable mActionBarBackground;
    private ColorDrawable mMainContentBackground;
    private LpActionButton mLpActionButton;

    private int mCageCounter;
    private boolean isToggled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mActionBarBackground = new ColorDrawable(getResources().getColor(R.color.dark_green));
        mMainContentBackground = new ColorDrawable(getResources().getColor(R.color.dark_green));
        getSupportActionBar().setBackgroundDrawable(mActionBarBackground);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.main_layout);
        linearLayout.setBackground(mMainContentBackground);

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
                    final int colorFrom;
                    final int colorTo;
                    if(isToggled) {
                        colorFrom = mActionBarBackground.getColor();
                        colorTo = getResources().getColor(R.color.purple);

                    } else {
                        colorFrom = mActionBarBackground.getColor();
                        colorTo = getResources().getColor(R.color.dark_green);
                    }
                    changeActionBarColor(colorFrom,colorTo);
                    changeMainContentColor(colorFrom,colorTo,KulAnimator.DEF_DURATION);

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

    public void changeMainContentColor(int fromColor, int toColor, long duration){
        KulAnimator.changeColorDrawableAnimation(fromColor, toColor, duration, mMainContentBackground).start();
    }

    private void checkCage() {
        if(mCageCounter==4){
            mLpActionButton.setSecondDrawable(R.drawable.nick_cage);
            mCageCounter = 0;
        } else mCageCounter ++ ;
    }

}
