package com.penny.quick.ui.activities.money_transfer;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.google.gson.Gson;
import com.penny.core.APITags;
import com.penny.core.models.UserTransferModel;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.utils.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import javax.inject.Inject;

public class MoneyTransferNumberActivity extends BaseActivity implements TextWatcher {

  @Inject
  MoneyTransferActivityViewModel moneyTransferActivityViewModel;
  private AppCompatButton submitButton;
  private EditText mobileNumber, editTextName, otp1TV, otp2TV, otp3TV;
  private LinearLayout nameLayout, otpLayout;
  private String enteredMobileNumber = "";
  private TextView tvError;
  private int state = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_money_transfer_number);
    setUpToolBar();
    setTitle(getString(R.string.money_transfer));
    initUi();
    setListeners();
  }

  private void setListeners() {
    submitButton.setOnClickListener(view -> {
      tvError.setVisibility(View.GONE);
      if (!NetworkUtils.isConnected(this)) {
        toast(APITags.DEVICE_IS_OFFLINE);
        return;
      }
      if (state == 0) {
        verifyNumber();
      } else if (state == 1) {
        enrollNumber();
      } else if (state == 2) {
        verifyOtp();
      } else if (state == 3) {
        fetchRecipient();
      }
    });
    findViewById(R.id.resend_view).setOnClickListener(view -> resendOtp());
  }

  private void resendOtp() {
    if (!NetworkUtils.isConnected(this)) {
      toast(APITags.DEVICE_IS_OFFLINE);
      return;
    }
    moneyTransferActivityViewModel.resendOtp(enteredMobileNumber).observe(this, workInfo -> {
      if (workInfo != null) {
        apiResponseHandler(workInfo);
      }
    });
  }

  private void verifyOtp() {
    String otp =
        otp1TV.getText().toString().trim() + otp2TV.getText().toString().trim() + otp3TV.getText()
            .toString().trim();
    if (otp.length() != 3) {
      showError(getString(R.string.otp_error_msg));
      return;
    }
    moneyTransferActivityViewModel.verifyOtp(enteredMobileNumber, otp)
        .observe(this, this::verifyOtpObserver);
  }

  private void verifyOtpObserver(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
      if (workInfo.getState() == State.SUCCEEDED) {
        if (!NetworkUtils.isConnected(MoneyTransferNumberActivity.this)) {
          toast(APITags.DEVICE_IS_OFFLINE);
          return;
        }
        fetchRecipient();
      }
    }
  }

  private void fetchRecipient() {
    moneyTransferActivityViewModel.fetchRecipient(enteredMobileNumber).observe(this, workInfo -> {
      if (workInfo != null) {
        apiResponseHandler(workInfo);
        if (workInfo.getState() == State.SUCCEEDED) {
          Intent intent;
          if (workInfo.getOutputData().getBoolean(ProjectConstants.RECIPIENT_AVAILABLE, false)) {
            intent = new Intent(this, SelectRecipientActivity.class);
          } else {
            intent = new Intent(this, AddRecipientActivity.class);
            intent.putExtra(ProjectConstants.IS_FROM_SELECT_RECIPIENT, false);
          }
          intent.putExtra(ProjectConstants.CUSTOMER_ID, enteredMobileNumber);
          startActivity(intent);
          finish();
        }
      }
    });
  }

  private void verifyNumber() {
    String mobileNumberText = mobileNumber.getText().toString().trim();
    if (StringUtils.isMobileNoValid(mobileNumberText)) {
      showError(getString(R.string.mobile_number_incorrect));
      return;
    }
    moneyTransferActivityViewModel.verifyMobileNumber(mobileNumberText)
        .observe(this, this::verifyNumberApiObserver);
  }

  private void showError(String error) {
    tvError.invalidate();
    tvError.setVisibility(View.VISIBLE);
    tvError.setText(error);
  }

  private void enrollNumber() {
    String name = editTextName.getText().toString();
    if (StringUtils.isEmptyString(name)) {
      showError(getString(R.string.name_error));
      return;
    }
    moneyTransferActivityViewModel.enrollMobileNumber(enteredMobileNumber, name).observe(this,
        this::enrollApiObserver);
  }

  private void enrollApiObserver(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
      if (workInfo.getState() == State.SUCCEEDED) {
        int transType = workInfo.getOutputData().getInt(ProjectConstants.TRANSACTION, 0);
        if (transType == 33) {
          editTextName.setEnabled(false);
          editTextName.setClickable(false);
          otpLayout.setVisibility(View.VISIBLE);
          state = 2;
        } else if (transType == 17) {
          fetchRecipient();
        }
      }
    }
  }

  private void verifyNumberApiObserver(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
      if (workInfo.getState() == State.SUCCEEDED) {
        int transType = workInfo.getOutputData().getInt(ProjectConstants.TRANSACTION, 0);
        String userTransfer = workInfo.getOutputData()
            .getString(ProjectConstants.USER_TRANSFER_MODEL);
        UserTransferModel userTransferModel = new Gson()
            .fromJson(userTransfer, UserTransferModel.class);
        enteredMobileNumber = mobileNumber.getText().toString().trim();
        if (transType == 323 || transType == 37) {
          mobileNumber.setEnabled(false);
          mobileNumber.setClickable(false);
          nameLayout.setVisibility(View.VISIBLE);
          if (userTransferModel.getName() != null && userTransferModel.getName().length() > 0) {
            editTextName.setText(userTransferModel.getName());
          }
          submitButton.setText(getString(R.string.submit));
          state = 1;
        } else if (transType == 33) {
          fetchRecipient();
        }
      }
    }
  }

  private void initUi() {
    submitButton = findViewById(R.id.verify);
    mobileNumber = findViewById(R.id.et_mobile_number);
    nameLayout = findViewById(R.id.nameLayout);
    editTextName = findViewById(R.id.et_customer_name);
    otpLayout = findViewById(R.id.otp_layout);
    otp1TV = findViewById(R.id.et_otp1);
    otp1TV.addTextChangedListener(this);
    otp2TV = findViewById(R.id.et_otp2);
    otp2TV.addTextChangedListener(this);
    otp3TV = findViewById(R.id.et_otp3);
    otp3TV.addTextChangedListener(this);
    tvError = findViewById(R.id.errorText);
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
    } else if (editable.length() == 0) {
      if (otp3TV.length() == 0) {
        otp2TV.requestFocus();
      }
      if (otp2TV.length() == 0) {
        otp1TV.requestFocus();
      }
    }
  }
}