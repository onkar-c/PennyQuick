package com.penny.quick.ui.activities.forgot_pwd_new_pwd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.database.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordViewModel;
import com.penny.quick.ui.activities.login.SignInActivity;
import com.penny.quick.utils.ToolBarUtils;
import com.penny.quick.utils.UiUtils;
import javax.inject.Inject;

public class ForgotPasswordNewPwdActivity extends BaseActivity {

  @Inject
  ForgotPasswordViewModel forgotPasswordViewModel;
  private EditText password, confirmPassword;
  private TextView tv_error;
  OnClickListener onDoneClick = view -> {
    if (validateFields()) {
//      changePassword();
      changePasswordSuccess();
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_pwd_new);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.forgot_pwd));
    initUi();
  }

  private void initUi() {
    password = findViewById(R.id.et_new_password);
    confirmPassword = findViewById(R.id.et_reenter_password);
    tv_error = findViewById(R.id.tv_error);
    findViewById(R.id.bt_change_pwd)
        .setOnClickListener(onDoneClick);
    UiUtils.setDisplayPasswordListener(password);
    UiUtils.setDisplayPasswordListener(confirmPassword);
  }

  private void changePassword() {
    forgotPasswordViewModel.changePassword(password.getText().toString()).observe(this,
        this::observeChangePasswordApi);
  }

  private void observeChangePasswordApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        changePasswordSuccess();
      }
    }
  }

  private void changePasswordSuccess() {
    onBackPressed();
  }

  @Override
  public void responseErrorHandling(int pApiId, String error) {
    showError(error);
  }

  private boolean validateFields() {
    if (StringUtils.isEmptyString(password.getText())) {
      showError(getString(R.string.new_password_blank_error));
      return false;
    }
    if (StringUtils.isEmptyString(confirmPassword.getText())) {
      showError(getString(R.string.re_enter_password_blank_error));
      return false;
    }
    if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
      showError(getString(R.string.password_mismatch_error));
      return false;
    }

    tv_error.setVisibility(View.GONE);
    return true;
  }

  private void showError(String error) {
    tv_error.setVisibility(View.VISIBLE);
    tv_error.setText(error);
  }
}
