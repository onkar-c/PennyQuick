package com.penny.quick.ui.activities.transaction_status;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.google.gson.Gson;
import com.penny.core.models.TransactionResponse;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivity;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class TransactionStatusActivity extends BaseActivity {

  @Inject
  TransactionStatusActivityViewModel transactionStatusActivityViewModel;

  private LinearLayout statusLayout;
  private TextView statusMainHeader, tvStatus;
  private TransactionResponse transactionResponse;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction_status);
    transactionResponse = (TransactionResponse) getIntent()
        .getSerializableExtra(ProjectConstants.TRANSACTION);
    initUI();
    setStatusWiseLayout(transactionResponse);
    findViewById(R.id.close).setOnClickListener(view -> onBackPressed());
    getTransactionStatus();
  }

  private void getTransactionStatus() {
    transactionStatusActivityViewModel.getStatus(transactionResponse.getTxnId()).observe(this,
        this::observeTransactionStatusApi);
  }

  private void observeTransactionStatusApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      if (state == State.SUCCEEDED) {
        transactionResponse = new Gson()
            .fromJson(workInfo.getOutputData().getString(ProjectConstants.TRANSACTION),
                TransactionResponse.class);
        if (transactionResponse.getStatus().equals(ProjectConstants.PENDING)) {
          try {
            Thread.sleep(4000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          getTransactionStatus();
        } else {
          setStatusWiseLayout(transactionResponse);
        }

      }
    }
  }

  private void startDisputeActivity() {
    Intent intent = new Intent(TransactionStatusActivity.this, ContactUsDisputeActivity.class);
    intent.putExtra(ProjectConstants.IS_DISPUTE, true);
    startActivity(intent);
  }

  private void setStatusWiseLayout(TransactionResponse transactionResponse) {

    switch (transactionResponse.getStatus()) {
      case ProjectConstants.PENDING:
        statusLayout.setBackground(ContextCompat.getDrawable(this, R.color.transaction_pending));
        statusMainHeader.setText(getString(R.string.transaction_pending));
        tvStatus.setText(getString(R.string.pending));
        break;
      case ProjectConstants.FAILURE:
        statusLayout.setBackground(ContextCompat.getDrawable(this, R.color.transaction_failed));
        statusMainHeader.setText(getString(R.string.transaction_failed));
        tvStatus.setText(getString(R.string.failed));
        break;
      default:
        statusLayout.setBackground(ContextCompat.getDrawable(this, R.color.transaction_success));
        statusMainHeader.setText(getString(R.string.transaction_success));
        tvStatus.setText(getString(R.string.success));
        break;
    }
    ((TextView) findViewById(R.id.transaction_status_header_date))
        .setText(transactionResponse.getDatetime());
    ((TextView) findViewById(R.id.dateTime)).setText(transactionResponse.getDatetime());
    ((TextView) findViewById(R.id.amount)).setText(
        String.format("%s %s", transactionResponse.getAmount(), getString(R.string.rupees_sign)));
    ((TextView) findViewById(R.id.transaction_id)).setText(transactionResponse.getTxnId());
    ((TextView) findViewById(R.id.customer_id)).setText(transactionResponse.getMobile());
    ((TextView) findViewById(R.id.rechargeType)).setText(
        String.format("%s %s", transactionResponse.getType(), getString(R.string.recharge)));
    Executors.newSingleThreadExecutor()
        .execute(() -> {
          String type = transactionStatusActivityViewModel
              .getType(transactionResponse.getOperator());
          runOnUiThread(() -> ((TextView) findViewById(R.id.type)).setText(type));
        });
  }

  private void initUI() {
    statusLayout = findViewById(R.id.transaction_page);
    statusMainHeader = findViewById(R.id.transaction_status_header);
    tvStatus = findViewById(R.id.status);
    findViewById(R.id.dispute).setOnClickListener(view -> startDisputeActivity());
  }
}