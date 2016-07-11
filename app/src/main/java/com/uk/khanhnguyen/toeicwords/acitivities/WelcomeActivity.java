package com.uk.khanhnguyen.toeicwords.acitivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uk.khanhnguyen.toeicwords.R;
import com.uk.khanhnguyen.toeicwords.adapters.ViewPagerAdapter;
import com.uk.khanhnguyen.toeicwords.utils.PrefManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.uk.khanhnguyen.toeicwords.utils.ToeicUtils.changeStatusBarColor;

public class WelcomeActivity extends Activity implements View.OnClickListener{

    private static final String TAG = "WelcomeActivity";
    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private LinearLayout mDotsLayout;
    private TextView[] mDotsList;
    private int[] mLayoutsList;
    private Button mBtnSkip, mBtnNext;
    private PrefManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName() + ": entered onCreate()");
        // Checking for first time launch -  before calling setContentView()
        mPrefManager = new PrefManager(this);
        if (!mPrefManager.isFirstTimeLaunch()) {
            Log.d(TAG, getClass().getSimpleName() + ": entered onCreate() not first time");
            launchHomeScreen();
            finish();
        }
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_welcome);

        // Init components
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        mBtnSkip = (Button) findViewById(R.id.btn_skip);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        initList();

        // adding bottom dots list
        addBottomDots(0);
        // make sure notification bar is transparent
        changeStatusBarColor(this);

        mViewPagerAdapter = new ViewPagerAdapter(this, mLayoutsList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        mBtnNext.setOnClickListener(this);
        mBtnSkip.setOnClickListener(this);

    }

    /**
     * Init layouts and dots in layout
     */
    void initList() {
        mLayoutsList = new int[]{
                R.layout.welcome_slide1,
                R.layout.welcome_slide2,
                R.layout.welcome_slide3,
                R.layout.welcome_slide4};
        mDotsList = new TextView[mLayoutsList.length];
    }

    /**
     * Destroy layouts and dots in layout
     */
    void destroyList() {
        mLayoutsList = null;
        mDotsList = null;
    }

    private void addBottomDots(int currentPage) {
        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDotsList.length; i++) {
            mDotsList[i] = new TextView(this);
            mDotsList[i].setText(Html.fromHtml("&#8226;"));
            mDotsList[i].setTextSize(35);
            mDotsList[i].setTextColor(colorsInactive[currentPage]);
            mDotsLayout.addView(mDotsList[i]);
        }

        if (mDotsList.length > 0)
            mDotsList[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void launchHomeScreen() {
        //mPrefManager.setFirstTimeLaunch(false);
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, getClass().getSimpleName() + ": entered onClick()");
        switch (view.getId()){
            case R.id.btn_skip:
                Log.d(TAG, getClass().getSimpleName() + ": entered onClick() btn_skip");
                launchHomeScreen();
                break;
            case R.id.btn_next:
                Log.d(TAG, getClass().getSimpleName() + ": entered onClick() btn_next");
                int current = getItem(+1);
                if (current < mLayoutsList.length) {
                    mViewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
                break;
            default:
                break;
        }
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == mLayoutsList.length - 1) {
                // last page. make button text to GOT IT
                mBtnNext.setText(getString(R.string.start));
                mBtnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                mBtnNext.setText(getString(R.string.next));
                mBtnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyList();
    }
}
