package com.penny.quick.ui.activities.transaction_status;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivity;
import javax.inject.Inject;

public class TransactionStatusActivity extends BaseActivity {

  @Inject
  TransactionStatusActivityViewModel transactionStatusActivityViewModel;

  private LinearLayout statusLayout;
  private TextView statusMainHeader, tvStatus;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction_status);
    initUI();
    getTransactionStatus();
    setStatusWiseLayout(R.color.transaction_pending, getString(R.string.transaction_pending),
        getString(R.string.pending));

    Thread myThread =
        new Thread() {
          @Override
          public void run() {
            try {
              Thread.sleep(2000);
              setStatusWiseLayout(R.color.transaction_failed,
                  getString(R.string.transaction_failed),
                  getString(R.string.failed));
              Thread.sleep(2000);
              setStatusWiseLayout(R.color.transaction_success,
                  getString(R.string.transaction_success),
                  getString(R.string.success));
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        };
    myThread.start();
    ((ImageView) findViewById(R.id.close)).setOnClickListener(view -> onBackPressed());
  }

  private void getTransactionStatus() {
    transactionStatusActivityViewModel.getStatus("").observe(this,
        this::observeTransactionStatusApi);
  }

  private void observeTransactionStatusApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {

      }
    }
  }

  private void startDisputeActivity() {
    Intent intent = new Intent(TransactionStatusActivity.this, ContactUsDisputeActivity.class);
    intent.putExtra(ProjectConstants.IS_DISPUTE, true);
    startActivity(intent);
  }

  private void setStatusWiseLayout(int background, String status, String subStatus) {
    runOnUiThread(() -> {
      statusLayout.setBackground(ContextCompat.getDrawable(this, background));
      statusMainHeader.setText(status);
      tvStatus.setText(subStatus);
    });

  }

  private void initUI() {
    statusLayout = findViewById(R.id.transaction_page);
    statusMainHeader = findViewById(R.id.transaction_status_header);
    tvStatus = findViewById(R.id.status);
    ((TextView) findViewById(R.id.dispute)).setOnClickListener(view -> startDisputeActivity());
  }
}