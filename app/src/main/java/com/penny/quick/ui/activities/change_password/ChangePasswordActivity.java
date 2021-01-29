package com.penny.quick.ui.activities.change_password;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.core.util.NetworkUtils;
import com.penny.database.utils.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordViewModel;
import com.penny.quick.utils.ToolBarUtils;
import com.penny.quick.utils.UiUtils;
import javax.inject.Inject;

public class ChangePasswordActivity extends BaseActivity {

  @Inject
  ForgotPasswordViewModel forgotPasswordViewModel;

  private EditText cntPwdET, newPwdET, rePwdET;
  private TextView errorTV;
  private final OnClickListener onChangePwdClick = view -> {
    if (validatePasswords()) {
      changePassword(cntPwdET.getText().toString(), newPwdET.getText().toString());
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_change_pasword);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.change_pwd));
    registerNetworkReceiver();
    initUi();
  }

  private void initUi() {
    cntPwdET = findViewById(R.id.et_current_password);
    UiUtils.setDisplayPasswordListener(cntPwdET);
    newPwdET = findViewById(R.id.et_new_password);
    UiUtils.setDisplayPasswordListener(newPwdET);
    rePwdET = findViewById(R.id.et_re_enter_password);
    UiUtils.setDisplayPasswordListener(rePwdET);
    errorTV = findViewById(R.id.tv_error);
    findViewById(R.id.bt_change_pwd).setOnClickListener(onChangePwdClick);
  }

  private boolean validatePasswords() {
    if (StringUtils.isPasswordValid(cntPwdET.getText().toString())) {
      showError(getString(R.string.cnt_pwd_error));
      return false;
    } else if (StringUtils.isPasswordValid(newPwdET.getText().toString())) {
      showError(getString(R.string.new_pwd_error));
      return false;
    } else if (StringUtils.isPasswordValid(rePwdET.getText().toString())) {
      showError(getString(R.string.re_pwd_error));
      return false;
    } else if (!newPwdET.getText().toString().equals(rePwdET.getText().toString())) {
      showError(getString(R.string.pwd_not_match_error));
      return false;
    }
    errorTV.setVisibility(View.GONE);
    return true;
  }

  private void showError(String error) {
    errorTV.setVisibility(View.VISIBLE);
    errorTV.setText(error);
  }

  private void changePassword(String oldPassword, String newPassword) {
    if (NetworkUtils.isConnected(this)) {
      forgotPasswordViewModel.changePassword(null, null, oldPassword, newPassword, false)
          .observe(this,
              this::observeChangePassword);
    }
  }

  private void observeChangePassword(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
      if(workInfo.getState() == State.SUCCEEDED) {
        showMessageDialog("Alert", "Password Changed Succesfully.", (dialogInterface, i) -> {
          dialogInterface.dismiss();
          finish();
        });
      }

    }
  }


  @Override
  protected void onResume() {
    super.onResume();
    if (!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
  }
}