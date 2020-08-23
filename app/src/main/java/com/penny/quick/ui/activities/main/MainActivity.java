package com.penny.quick.ui.activities.main;

import android.os.Bundle;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.core.APITags;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject MainActivityViewModel mainActivityViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  private void login(String userName, String password) {
//    mainActivityViewModel.performLogin(userName, password).observe(this, this::observeAPIStatus);
  }

//  private void observeAPIStatus(WorkInfo workInfo) {
//    if (workInfo != null) {
//      State state = workInfo.getState();
//      apiResponseHandler(workInfo);
//      if (state == State.SUCCEEDED) {}
//    }
//  }

  @Override
  public void responseErrorHandling(int apiId, String error) {
    if (apiId == APITags.API_LOGIN) {}
  }
}
