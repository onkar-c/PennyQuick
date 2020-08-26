package com.penny.quick.ui.activities.change_password;

import android.os.Bundle;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordViewModel;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class ChangePasswordActivity extends BaseActivity {

  @Inject
  ForgotPasswordViewModel forgotPasswordViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_change_pasword);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.change_pwd));
  }

  private void changePassword(String oldPassword, String newPassword) {
    forgotPasswordViewModel.changePassword(null, null, oldPassword, newPassword, false)
        .observe(this,
            this::observeChangePassword);
  }

  private void observeChangePassword(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        changePasswordSuccess();
      }
    }
  }

  private void changePasswordSuccess() {

  }
}