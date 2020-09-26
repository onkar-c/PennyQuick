package com.penny.quick.ui.activities.main;

import android.os.Bundle;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject
  MainActivityViewModel mainActivityViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }


  @Override
  public void responseErrorHandling(int apiId, String error) {

  }
}
