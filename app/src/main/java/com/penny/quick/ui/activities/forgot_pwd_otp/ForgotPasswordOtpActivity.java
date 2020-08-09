package com.penny.quick.ui.activities.forgot_pwd_otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.quick.R;
import com.penny.quick.ui.activities.forgot_pwd_new_pwd.ForgotPasswordNewPwdActivity;
import com.penny.quick.utils.ToolBarUtils;

public class ForgotPasswordOtpActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_pwd_otp);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.forgot_pwd));

    findViewById(R.id.bt_done)
        .setOnClickListener(onDoneClick);
  }

  OnClickListener onDoneClick = view -> {
    startActivity(new Intent(ForgotPasswordOtpActivity.this, ForgotPasswordNewPwdActivity.class));
    finish();
  };
}
