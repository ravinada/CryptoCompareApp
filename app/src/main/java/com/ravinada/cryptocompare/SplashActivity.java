package com.ravinada.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    //if (PreferenceData.isLogin()){
                    //    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    //    startActivity(i);
                    //    overridePendingTransition(0, 0);
                    //    finish();
                    //}else{
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        finish();
                    //}
            }
        }, SPLASH_TIME_OUT);
    }
}
