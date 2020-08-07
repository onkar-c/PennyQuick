package com.penny.quick.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.intro_screen.IntroScreen;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

  @Inject SplashActivityViewModel splashActivityViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Thread myThread =
        new Thread() {
          @Override
          public void run() {
            try {
              sleep(1200);
              Intent intent = new Intent(getApplicationContext(), IntroScreen.class);
              startActivity(intent);
              finish();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };
    myThread.start();
  }
}
