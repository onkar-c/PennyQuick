package com.penny.quick.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import javax.inject.Inject;

public class SignInActivity extends BaseActivity {

  @Inject
  SignActivityViewModel signActivityViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    findViewById(R.id.bt_sign_in)
        .setOnClickListener(onSignClick);

    findViewById(R.id.tx_forgot_password)
        .setOnClickListener(onForgotClick);
  }

  OnClickListener onSignClick = view -> {
    startActivity(new Intent(SignInActivity.this, SuccessLoginActivity.class));
    finish();
  };

  OnClickListener onForgotClick = view -> {
    startActivity(new Intent(SignInActivity.this, ForgotPasswordMobRegActivity.class));
  };

}
