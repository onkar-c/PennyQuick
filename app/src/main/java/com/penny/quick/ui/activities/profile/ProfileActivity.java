package com.penny.quick.ui.activities.profile;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;

public class ProfileActivity extends BaseActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    setUpToolBar();
    setTitle("Profile");
  }
}
