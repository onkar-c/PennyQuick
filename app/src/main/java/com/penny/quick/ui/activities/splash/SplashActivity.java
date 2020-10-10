package com.penny.quick.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penny.database.CoreSharedHelper;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.dash_board.DashBoardActivity;
import com.penny.quick.ui.activities.intro_screen.IntroScreen;
import com.penny.quick.ui.activities.login.SignInActivity;
import com.penny.quick.utils.CommonUtils;
import java.util.List;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

  @Inject
  SplashActivityViewModel splashActivityViewModel;

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
              Intent intent;
              if (CoreSharedHelper.getInstance().isFirstInstall()) {
               /* List<Operators> operatorsList = new Gson()
                    .fromJson(CommonUtils.loadData("operatorData.json", SplashActivity.this),
                        new TypeToken<List<Operators>>() {
                        }.getType());
                splashActivityViewModel.saveOperators(operatorsList);*/
                List<com.penny.database.entities.State> statesList = new Gson()
                    .fromJson(CommonUtils.loadData("StatesData.json", SplashActivity.this),
                        new TypeToken<List<com.penny.database.entities.State>>() {
                        }.getType());
                splashActivityViewModel.saveStates(statesList);
                intent = new Intent(getApplicationContext(), IntroScreen.class);
              } else if (CoreSharedHelper.getInstance().isLogin() && CoreSharedHelper.getInstance()
                  .isRememberPassword()) {
                intent = new Intent(getApplicationContext(), DashBoardActivity.class);
              } else {
                intent = new Intent(getApplicationContext(), SignInActivity.class);
              }
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
