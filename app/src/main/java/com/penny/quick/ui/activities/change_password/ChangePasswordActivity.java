package com.penny.quick.ui.activities.change_password;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.penny.quick.R;
import com.penny.quick.utils.ToolBarUtils;

public class ChangePasswordActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_change_pasword);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.change_pwd));
  }
}