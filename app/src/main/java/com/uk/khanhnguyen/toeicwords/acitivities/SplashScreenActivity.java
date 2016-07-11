package com.uk.khanhnguyen.toeicwords.acitivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.uk.khanhnguyen.toeicwords.R;
import com.uk.khanhnguyen.toeicwords.utils.ToeicUtils;

public class SplashScreenActivity extends Activity {

    private static final int SPLASH_SCREEN_TIMEOUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash_screen);

        ToeicUtils.changeStatusBarColor(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }



}
