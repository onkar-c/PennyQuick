package com.penny.quick.ui.activities.forgot_pwd_mob;

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
import com.penny.quick.ui.activities.forgot_pwd_otp.ForgotPasswordOtpActivity;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class ForgotPasswordMobRegActivity extends BaseActivity {

  @Inject
  ForgotPasswordViewModel forgotPasswordViewModel;
  private TextView tv_error;

  private EditText mobileNumber;
  private OnClickListener onNextClick = view -> {
    if (validateFields()) {
      requestOTP();
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_pwd_mob_no);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.forgot_pwd));
    initUi();

  }

  private void initUi() {
    mobileNumber = findViewById(R.id.et_mob_no);
    tv_error = findViewById(R.id.tv_error);
    findViewById(R.id.bt_next)
        .setOnClickListener(onNextClick);
  }

  private void requestOTP() {
    forgotPasswordViewModel.requestOTP(mobileNumber.getText().toString()).observe(this,
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
    Intent intent = new Intent(ForgotPasswordMobRegActivity.this, ForgotPasswordOtpActivity.class);
    intent.putExtra(ProjectConstants.MOBILE_NUMBER, mobileNumber.getText().toString());
    startActivity(intent);
    finish();
  }

  @Override
  public void responseErrorHandling(int pApiId, String error) {
    showError(error);
  }

  private boolean validateFields() {
    if (!StringUtils.isMobileNoValid(mobileNumber.getText().toString())) {
      showError(getString(R.string.mobile_number_incorrect));
      return false;
    }

    tv_error.setVisibility(View.GONE);
    return true;
  }

  private void showError(String error) {
    tv_error.invalidate();
    tv_error.setVisibility(View.VISIBLE);
    tv_error.setText(error);
  }
}
