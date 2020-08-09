package com.penny.quick.ui.activities.forgot_pwd_mob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.quick.R;
import com.penny.quick.ui.activities.forgot_pwd_otp.ForgotPasswordOtpActivity;
import com.penny.quick.utils.ToolBarUtils;

public class ForgotPasswordMobRegActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_pwd_mob_no);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.forgot_pwd));

    findViewById(R.id.bt_next)
        .setOnClickListener(onNextClick);
  }

  OnClickListener onNextClick = view -> {
    startActivity(new Intent(ForgotPasswordMobRegActivity.this, ForgotPasswordOtpActivity.class));
    finish();
  };
}
