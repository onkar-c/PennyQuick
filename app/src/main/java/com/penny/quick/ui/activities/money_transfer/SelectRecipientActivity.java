package com.penny.quick.ui.activities.money_transfer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.work.WorkInfo.State;
import com.google.gson.Gson;
import com.penny.core.APITags;
import com.penny.core.models.TransactionResponse;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.Recipient;
import com.penny.database.utils.StringUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivity;
import com.penny.quick.ui.adapters.RecipientBottomSheetAdapter.RecipientBottomSheetListItemClickListener;
import com.penny.quick.utils.RecipientBottomSheetDialog;
import java.util.List;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class SelectRecipientActivity extends BaseActivity implements
    RecipientBottomSheetListItemClickListener {

  RecipientBottomSheetDialog recipientBottomSheetDialog;
  @Inject
  MoneyTransferActivityViewModel moneyTransferActivityViewModel;
  private String selectedNumber = "";
  private Recipient selectedRecipient = null;
  private EditText accountNumber, amount, documentId, pinCode;
  private TextView recipient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_recepient);
    setUpToolBar();
    setTitle(getString(R.string.money_transfer));
    selectedNumber = getIntent().getStringExtra(ProjectConstants.CUSTOMER_ID);
    initUi();
    setListeners();

  }

  private void initUi() {
    recipient = findViewById(R.id.et_recipient);
    accountNumber = findViewById(R.id.et_account_number);
    amount = findViewById(R.id.amount);
    documentId = findViewById(R.id.id_value);
    pinCode = findViewById(R.id.pincode);
  }

  private void selectRecipient() {
    if (selectedRecipient == null) {
      toast(getString(R.string.recipient_error_text));
      return;
    }
    String accountNumberText = accountNumber.getText().toString().trim();
    if (StringUtils.isEmptyString(accountNumberText)) {
      toast(getString(R.string.account_number_error));
      return;
    }
    String amountText = amount.getText().toString().trim();
    if (StringUtils.isEmptyString(amountText)) {
      toast(getString(R.string.amount_error_text));
      return;
    }
    String documentIdText = documentId.getText().toString().trim();
    if (StringUtils.isEmptyString(documentIdText)) {
      toast(getString(R.string.account_id__number_error));
      return;
    }
    String pincodeText = pinCode.getText().toString().trim();
    if (StringUtils.isEmptyString(pincodeText)) {
      toast(getString(R.string.pincode_number_incorrect));
      return;
    }
    if (!NetworkUtils.isConnected(this)) {
      toast(APITags.DEVICE_IS_OFFLINE);
      return;
    }
    moneyTransferActivityViewModel.transferMoney().observe(this, workInfo -> {
      if(workInfo != null) {
        apiResponseHandler(workInfo);
        if(workInfo.getState() == State.SUCCEEDED) {
          moneyTrasferApiSuccess(workInfo.getOutputData().getString(ProjectConstants.TRANSACTION));
        }
      }
    });
  }
  private void moneyTrasferApiSuccess(String transaction) {
    Intent intent = new Intent(this,
        TransactionStatusActivity.class);
    intent.putExtra(ProjectConstants.TRANSACTION,
        new Gson().fromJson(transaction, TransactionResponse.class));
    startActivity(intent);
  }

  private void setListeners() {
    findViewById(R.id.et_add_recepient)
        .setOnClickListener(view -> {
          Intent intent = new Intent(this, AddRecipientActivity.class);
          intent.putExtra(ProjectConstants.CUSTOMER_ID, selectedNumber);
          startActivity(intent);
        });

    recipient.setOnClickListener(view -> Executors.newSingleThreadExecutor()
        .execute(() -> {
          List<Recipient> recipients = moneyTransferActivityViewModel.getRecipients();
          if (recipients != null && recipients.size() > 0) {
            recipientBottomSheetDialog = new RecipientBottomSheetDialog(
                recipients);
            recipientBottomSheetDialog
                .show(getSupportFragmentManager(), ProjectConstants.RECIPIENT);
          } else {
            runOnUiThread(() -> toast(getString(R.string.recipient_error)));
          }
        }));
    findViewById(R.id.submit).setOnClickListener(view -> selectRecipient());
  }


  @Override
  public void onBottomSheetListItemClick(Recipient obj) {
    if (recipientBottomSheetDialog != null && recipientBottomSheetDialog.isVisible()) {
      recipientBottomSheetDialog.dismiss();
    }
    selectedRecipient = obj;
    recipient.setText(obj.getRecipient_name());
  }
}