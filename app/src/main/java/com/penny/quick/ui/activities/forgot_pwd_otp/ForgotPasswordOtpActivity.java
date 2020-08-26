package com.penny.quick.ui.activities.forgot_pwd_otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.database.ProjectConstants;
import com.penny.database.StringUtils;
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
  private EditText otp1TV, otp2TV, otp3TV, otp4TV;
  OnClickListener onDoneClick = view -> verifyOtp();

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
    otp1TV = findViewById(R.id.et_otp1);
    otp2TV = findViewById(R.id.et_otp2);
    otp3TV = findViewById(R.id.et_otp3);
    otp4TV = findViewById(R.id.et_otp4);
  }

  private void requestOTP() {
    forgotPasswordViewModel.requestOTP(getIntent().getStringExtra(ProjectConstants.MOBILE_NUMBER))
        .observe(this,
            this::observeRequestOtpApi);
  }

  private void verifyOtp() {
    String otp =
        otp1TV.getText().toString().trim() + otp2TV.getText().toString().trim() + otp3TV.getText()
            .toString().trim()
            + otp4TV.getText().toString().trim();
    forgotPasswordViewModel
        .verifyOTP(getIntent().getStringExtra(ProjectConstants.MOBILE_NUMBER), otp).observe(this,
        this::observeVerifyOtpApi);
  }

  private void observeVerifyOtpApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        verifyOtpSuccess();
      }
    }
  }

  private void verifyOtpSuccess() {
    if (StringUtils.isOtpValid(otp1TV.getText().toString(), otp2TV.getText().toString(),
        otp3TV.getText().toString(), otp4TV.getText().toString())) {
      tv_error.setVisibility(View.GONE);
      Intent intent = new Intent(ForgotPasswordOtpActivity.this,
          ForgotPasswordNewPwdActivity.class);
      String otp =
          otp1TV.getText().toString().trim() + otp2TV.getText().toString().trim() + otp3TV.getText()
              .toString().trim()
              + otp4TV.getText().toString().trim();
      intent.putExtra(ProjectConstants.OTP, otp);
      intent.putExtra(ProjectConstants.MOBILE_NUMBER,
          getIntent().getStringExtra(ProjectConstants.MOBILE_NUMBER));
      startActivity(intent);
      finish();
    } else {
      showError(getString(R.string.otp_error));
    }
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
