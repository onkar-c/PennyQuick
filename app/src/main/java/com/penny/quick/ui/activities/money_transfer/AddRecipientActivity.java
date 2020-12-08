package com.penny.quick.ui.activities.money_transfer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.core.APITags;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.BankDetails;
import com.penny.database.utils.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.adapters.BankBottomSheetAdapter.BankBottomSheetListItemClickListener;
import com.penny.quick.utils.BankBottomSheetDialog;
import java.util.List;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class AddRecipientActivity extends BaseActivity implements
    BankBottomSheetListItemClickListener {

  BankBottomSheetDialog bankBottomSheetDialog;
  @Inject
  MoneyTransferActivityViewModel moneyTransferActivityViewModel;
  private BankDetails selectedBank = null;
  private TextView bankTv;
  private String customerId;
  private boolean isFromMoneyTransfer = false;
  private EditText accountNumber, ifsc, mobileNumber, name;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_recepient);
    setUpToolBar();
    setTitle(getString(R.string.add_recepient));
    customerId = getIntent().getStringExtra(ProjectConstants.CUSTOMER_ID);
    isFromMoneyTransfer = getIntent().getBooleanExtra(ProjectConstants.IS_NUMBER_VERIFIED, false);
    initUi();
    initListeners();
  }

  private void initListeners() {
    bankTv.setOnClickListener(view -> Executors.newSingleThreadExecutor()
        .execute(() -> {
          List<BankDetails> banks = moneyTransferActivityViewModel.getBanks();
          if (banks != null && banks.size() > 0) {
            bankBottomSheetDialog = new BankBottomSheetDialog(
                banks);
            bankBottomSheetDialog
                .show(getSupportFragmentManager(), ProjectConstants.BANK);
          } else {
            runOnUiThread(() -> toast(getString(R.string.bank_error)));
          }
        }));
    findViewById(R.id.submit).setOnClickListener(view -> addRecipient());
  }

  private void addRecipient() {
    if (selectedBank == null) {
      toast(getString(R.string.bank_error_text));
      return;
    }

    String accountNumberText = accountNumber.getText().toString().trim();
    if (StringUtils.isEmptyString(accountNumberText)) {
      toast(getString(R.string.account_number_error));
      return;
    }

    String nameText = name.getText().toString().trim();
    if (StringUtils.isEmptyString(nameText)) {
      toast(getString(R.string.name_error_text));
      return;
    }
    String ifscText = ifsc.getText().toString().trim();
    if (StringUtils.isEmptyString(ifscText)) {
      toast(getString(R.string.ifsc_number_error));
      return;
    }
    String mobileNumberText = mobileNumber.getText().toString().trim();
    if (!StringUtils.isMobileNoValid(mobileNumberText)) {
      toast(getString(R.string.mobile_number_incorrect));
      return;
    }
    if (!NetworkUtils.isConnected(this)) {
      toast(APITags.DEVICE_IS_OFFLINE);
      return;
    }
    moneyTransferActivityViewModel
        .addRecipient(selectedBank.getBankCode(), accountNumberText, customerId, ifscText, nameText,
            mobileNumberText).observe(this, this::observeAddRecipientApi);
  }

  private void observeAddRecipientApi(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
      if (workInfo.getState() == State.SUCCEEDED) {
        if (isFromMoneyTransfer) {
          fetchRecipient();
        } else {
          finish();
        }
      }
    }
  }

  private void fetchRecipient() {
    moneyTransferActivityViewModel.fetchRecipient(customerId).observe(this, workInfo -> {
      if (workInfo != null) {
        apiResponseHandler(workInfo);
        if (workInfo.getState() == State.SUCCEEDED) {
          Intent intent;
          if (workInfo.getOutputData().getBoolean(ProjectConstants.RECIPIENT_AVAILABLE, false)) {
            intent = new Intent(this, SelectRecipientActivity.class);
            intent.putExtra(ProjectConstants.CUSTOMER_ID, customerId);
            startActivity(intent);
            finish();
          }
        }
      }
    });
  }

  private void initUi() {
    bankTv = findViewById(R.id.tv_bank);
    accountNumber = findViewById(R.id.et_account_number);
    ifsc = findViewById(R.id.ifsc);
    mobileNumber = findViewById(R.id.et_mobile_number);
    name = findViewById(R.id.name);
  }

  @Override
  public void onBottomSheetListItemClick(BankDetails obj) {
    if (bankBottomSheetDialog != null && bankBottomSheetDialog.isVisible()) {
      bankBottomSheetDialog.dismiss();
    }
    selectedBank = obj;
    bankTv.setText(obj.getBankName());
  }
}