package com.penny.quick.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.penny.database.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordMobRegActivity;
import com.penny.quick.utils.UiUtils;
import javax.inject.Inject;

public class SignInActivity extends BaseActivity {

  @Inject
  SignActivityViewModel signActivityViewModel;
  private OnClickListener onForgotClick = view -> startActivity(
      new Intent(SignInActivity.this, ForgotPasswordMobRegActivity.class));
  private EditText userId, password;
  private TextView error;
  OnClickListener onSignClick = view -> {
    if (validateFields()) {
      startActivity(new Intent(SignInActivity.this, SuccessLoginActivity.class));
      finish();
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    initUi();
  }

  private void initUi() {
    userId = findViewById(R.id.et_user_id);
    password = findViewById(R.id.et_password);
    error = findViewById(R.id.tv_error);
    findViewById(R.id.bt_sign_in)
        .setOnClickListener(onSignClick);

    findViewById(R.id.tx_forgot_password)
        .setOnClickListener(onForgotClick);

    UiUtils.setDisplayPasswordListener(password);
  }

  private boolean validateFields() {
    if (StringUtils.isEmptyString(userId.getText())) {
      error.setVisibility(View.VISIBLE);
      error.setText(getString(R.string.user_id_blank_error));
      return false;
    }
    if (StringUtils.isEmptyString(password.getText())) {
      error.setVisibility(View.VISIBLE);
      error.setText(getString(R.string.password_blank_error));
      return false;
    }
    error.setVisibility(View.GONE);
    return true;
  }

}
