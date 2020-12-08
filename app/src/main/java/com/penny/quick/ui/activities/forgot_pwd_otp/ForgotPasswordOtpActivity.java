package com.penny.quick.ui.activities.forgot_pwd_otp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.core.APITags;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.utils.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordViewModel;
import com.penny.quick.ui.activities.forgot_pwd_new_pwd.ForgotPasswordNewPwdActivity;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class ForgotPasswordOtpActivity extends BaseActivity implements TextWatcher {

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
    registerNetworkReceiver();
    initUi();
  }

  private void initUi() {
    findViewById(R.id.bt_done)
        .setOnClickListener(onDoneClick);
    findViewById(R.id.resend_otp).setOnClickListener(resendOTP);
    tv_error = findViewById(R.id.tv_error);
    otp1TV = findViewById(R.id.et_otp1);
    otp1TV.addTextChangedListener(this);
    otp2TV = findViewById(R.id.et_otp2);
    otp2TV.addTextChangedListener(this);
    otp3TV = findViewById(R.id.et_otp3);
    otp3TV.addTextChangedListener(this);
    otp4TV = findViewById(R.id.et_otp4);
    otp4TV.addTextChangedListener(this);
  }

  private void requestOTP() {
    if (NetworkUtils.isConnected(this)) {
      forgotPasswordViewModel.requestOTP(getIntent().getStringExtra(ProjectConstants.MOBILE_NUMBER))
          .observe(this,
              this::observeRequestOtpApi);
    }else{
      showError(APITags.DEVICE_IS_OFFLINE);
    }
  }

  private void verifyOtp() {
    if (NetworkUtils.isConnected(this)) {
      String otp =
          otp1TV.getText().toString().trim() + otp2TV.getText().toString().trim() + otp3TV.getText()
              .toString().trim()
              + otp4TV.getText().toString().trim();
      if(otp.length() != 4) {
        showError(getString(R.string.otp_error_msg));
        return;
      }
      forgotPasswordViewModel
          .verifyOTP(getIntent().getStringExtra(ProjectConstants.MOBILE_NUMBER), otp).observe(this,
          this::observeVerifyOtpApi);
    }else {
      showError(APITags.DEVICE_IS_OFFLINE);
    }
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
      apiResponseHandler(workInfo);
    }
  }

  @Override
  public void responseErrorHandling(int pApiId, String error) {
    showError(error);
  }

  private void showError(String error) {
    tv_error.setVisibility(View.VISIBLE);
    tv_error.setText(error);
  }

  @Override
  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

  }

  @Override
  public void afterTextChanged(Editable editable) {
    if (editable.length() == 1) {
      if (otp1TV.length() == 1) {
        otp2TV.requestFocus();
      }
      if (otp2TV.length() == 1) {
        otp3TV.requestFocus();
      }
      if (otp3TV.length() == 1) {
        otp4TV.requestFocus();
      }
    } else if (editable.length() == 0) {
      if (otp4TV.length() == 0) {
        otp3TV.requestFocus();
      }
      if (otp3TV.length() == 0) {
        otp2TV.requestFocus();
      }
      if (otp2TV.length() == 0) {
        otp1TV.requestFocus();
      }
    }
  }
}
