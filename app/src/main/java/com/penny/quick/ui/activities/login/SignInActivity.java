package com.penny.quick.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.core.APITags;
import com.penny.database.CoreSharedHelper;
import com.penny.database.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordMobRegActivity;
import com.penny.core.util.NetworkUtils;
import com.penny.quick.utils.UiUtils;
import javax.inject.Inject;

public class SignInActivity extends BaseActivity {

  @Inject
  SignActivityViewModel signActivityViewModel;
  private OnClickListener onForgotClick = view -> startActivity(
      new Intent(SignInActivity.this, ForgotPasswordMobRegActivity.class));
  private EditText userId, password;
  private TextView tv_error;
  private AppCompatCheckBox compatCheckBox;
  OnClickListener onSignClick = view -> {
    if (validateFields()) {
      if(NetworkUtils.isConnected(this)) {
//        login();
        loginSuccess();
      } else {
        showError(APITags.DEVICE_IS_OFFLINE);
      }
    }
  };

  private void login() {
    signActivityViewModel.performLogin(userId.getText().toString(), password.getText().toString()).observe(this,
        this::observeLoginApi);
  }

  private void observeLoginApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        loginSuccess();
      }
    }
  }

  @Override
  public void responseErrorHandling(int pApiId, String error) {
    showError(error);
  }

  private void loginSuccess() {
    CoreSharedHelper.getInstance().setIsLogin(true);
    CoreSharedHelper.getInstance().setRememberPassword(compatCheckBox.isChecked());
    startActivity(new Intent(SignInActivity.this, SuccessLoginActivity.class));
    finish();
  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    initUi();
  }

  private void initUi() {
    userId = findViewById(R.id.et_user_id);
    password = findViewById(R.id.et_password);
    tv_error = findViewById(R.id.tv_error);
    compatCheckBox = findViewById(R.id.cb_remember_me);
    findViewById(R.id.bt_sign_in)
        .setOnClickListener(onSignClick);

    findViewById(R.id.tx_forgot_password)
        .setOnClickListener(onForgotClick);

    UiUtils.setDisplayPasswordListener(password);
  }

  private boolean validateFields() {
    if (StringUtils.isEmptyString(userId.getText())) {
      showError(getString(R.string.user_id_blank_error));
      return false;
    }
    if (StringUtils.isEmptyString(password.getText())) {
      showError(getString(R.string.password_blank_error));
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
