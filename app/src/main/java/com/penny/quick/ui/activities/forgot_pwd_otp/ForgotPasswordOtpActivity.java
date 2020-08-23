package com.penny.quick.ui.activities.forgot_pwd_otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordViewModel;
import com.penny.quick.ui.activities.forgot_pwd_new_pwd.ForgotPasswordNewPwdActivity;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class ForgotPasswordOtpActivity extends BaseActivity {

  @Inject
  ForgotPasswordViewModel forgotPasswordViewModel;
  private TextView tv_error;
  OnClickListener resendOTP = view -> {
    tv_error.setVisibility(View.GONE);
    requestOTP();
  };
  OnClickListener onDoneClick = view -> {
    tv_error.setVisibility(View.GONE);
    startActivity(new Intent(ForgotPasswordOtpActivity.this, ForgotPasswordNewPwdActivity.class));
    finish();
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_pwd_otp);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.forgot_pwd));
    initUi();
  }

  private void initUi() {
    findViewById(R.id.bt_done)
        .setOnClickListener(onDoneClick);
    findViewById(R.id.resend_otp).setOnClickListener(resendOTP);
    tv_error = findViewById(R.id.tv_error);
  }

  private void requestOTP() {
    forgotPasswordViewModel.requestOTP(getIntent().getStringExtra(ProjectConstants.MOBILE_NUMBER))
        .observe(this,
            this::observeRequestOtpApi);
  }

  private void observeRequestOtpApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        requestOtpSuccess();
      }
    }
  }

  private void requestOtpSuccess() {
    startActivity(new Intent(ForgotPasswordOtpActivity.this, ForgotPasswordOtpActivity.class));
    finish();
  }

  @Override
  public void responseErrorHandling(int pApiId, String error) {
    showError(error);
  }

  private void showError(String error) {
    tv_error.setVisibility(View.VISIBLE);
    tv_error.setText(error);
  }

}
