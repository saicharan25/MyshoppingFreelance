package info.devexchanges.navvp.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import info.devexchanges.navvp.R;
import info.devexchanges.navvp.home.MainActivity;
import info.devexchanges.navvp.main.LoginActivity;


public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    class C02581 implements Runnable {
        C02581() {
        }

        public void run() {
            SplashScreen.this.startActivity(new Intent(SplashScreen.this, LoginActivity.class));
            SplashScreen.this.finish();
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash_screen);
       // getSupportActionBar().hide();
        new Handler().postDelayed(new C02581(), (long) SPLASH_TIME_OUT);
    }
}
