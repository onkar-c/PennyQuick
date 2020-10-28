package com.penny.quick.ui.activities.money_transfer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.Recipient;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
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
  private Recipient selectedRecipient = null;
  private TextView recipient;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_recepient);
    setUpToolBar();
    setTitle(getString(R.string.money_transfer));
    initUi();
    setListeners();

  }

  private void initUi() {
    recipient = findViewById(R.id.et_recipient);
  }

  private void setListeners() {
    findViewById(R.id.et_add_recepient)
        .setOnClickListener(view -> startActivity(new Intent(this, AddRecipientActivity.class)));

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