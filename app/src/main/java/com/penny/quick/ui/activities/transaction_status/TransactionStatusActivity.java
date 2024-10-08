package com.penny.quick.ui.activities.transaction_status;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.google.gson.Gson;
import com.penny.core.models.TransactionResponse;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.Operators;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivity;
import com.penny.quick.utils.CommonUtils;
import java.util.List;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class TransactionStatusActivity extends BaseActivity {

  @Inject
  TransactionStatusActivityViewModel transactionStatusActivityViewModel;

  private LinearLayout statusLayout;
  private TextView statusMainHeader, tvStatus;
  private TransactionResponse transactionResponse;
  private ImageView statusIcon, operatorIcon;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction_status);
    transactionResponse = (TransactionResponse) getIntent()
        .getSerializableExtra(ProjectConstants.TRANSACTION);
    initUI();
    setStatusWiseLayout(transactionResponse);
    if (transactionResponse.getOperator() != null
        && transactionResponse.getOperator().length() > 0) {
      Executors.newSingleThreadExecutor()
          .execute(() -> {
            List<Operators> operators = transactionStatusActivityViewModel
                .getOperator(transactionResponse.getOperator());
            runOnUiThread(() -> {
              if (operators != null && operators.size() > 0) {
                CommonUtils.getImage(this, operators.get(0).getUrl(),
                    operatorIcon, R.drawable.hamburger_icon);
              } else {
                operatorIcon.setVisibility(View.GONE);
              }
            });
          });
    } else {
      operatorIcon.setVisibility(View.GONE);
    }

    findViewById(R.id.close).setOnClickListener(view -> onBackPressed());
    getTransactionStatus();
  }

  private void getTransactionStatus() {
    if (NetworkUtils.isConnected(this)) {
      transactionStatusActivityViewModel.getStatus(transactionResponse.getTxnId(),
          transactionResponse.getType().equals(ProjectConstants.MONEY_TRANSFER)).observe(this,
          this::observeTransactionStatusApi);
    }
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
    finish();
  }

  private void setStatusWiseLayout(TransactionResponse transactionResponse) {

    switch (transactionResponse.getStatus()) {
      case ProjectConstants.PENDING:
        statusLayout.setBackground(ContextCompat.getDrawable(this, R.color.transaction_pending));
        statusIcon.setImageResource(R.drawable.pending);
        statusMainHeader.setText(getString(R.string.transaction_pending));
        tvStatus.setText(getString(R.string.pending));
        break;
      case ProjectConstants.FAILURE:
        statusLayout.setBackground(ContextCompat.getDrawable(this, R.color.transaction_failed));
        statusMainHeader.setText(getString(R.string.transaction_failed));
        statusIcon.setImageResource(R.drawable.failed);
        tvStatus.setText(getString(R.string.failed));
        break;
      default:
        statusLayout.setBackground(ContextCompat.getDrawable(this, R.color.transaction_success));
        statusIcon.setImageResource(R.drawable.success);
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
    if (transactionResponse.getType().equals(ProjectConstants.MONEY_TRANSFER)) {
      ((TextView) findViewById(R.id.rechargeType)).setText(ProjectConstants.MONEY_TRANSFER);
    } else {
      ((TextView) findViewById(R.id.rechargeType)).setText(
          String.format("%s %s",
              !transactionResponse.getType().equals(ProjectConstants.DTH) ? ProjectConstants.MOBILE
                  : transactionResponse.getType().equals(ProjectConstants.DTH),
              getString(R.string.recharge)));
    }
    if (transactionResponse.getOperator() != null
        && transactionResponse.getOperator().length() > 0) {
      Executors.newSingleThreadExecutor()
          .execute(() -> {
            String type = transactionStatusActivityViewModel
                .getType(transactionResponse.getOperator());
            runOnUiThread(() -> ((TextView) findViewById(R.id.type)).setText(type));
          });
    }
  }

  private void initUI() {
    statusLayout = findViewById(R.id.transaction_page);
    statusMainHeader = findViewById(R.id.transaction_status_header);
    tvStatus = findViewById(R.id.status);
    statusIcon = findViewById(R.id.status_icon);
    operatorIcon = findViewById(R.id.company_icon);
    findViewById(R.id.dispute).setOnClickListener(view -> startDisputeActivity());
  }
}