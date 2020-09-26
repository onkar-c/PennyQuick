package com.penny.quick.ui.activities.accept_details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivity;
import com.penny.quick.utils.ToolBarUtils;

public class AcceptRechargeDetails extends AppCompatActivity {

  private Intent intent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_accept_recharge_details);
    ToolBarUtils.setUpToolBar(this);
    intent = getIntent();
    intiUI();
    findViewById(R.id.bt_recharge)
        .setOnClickListener(view -> startActivity(new Intent(AcceptRechargeDetails.this,
            TransactionStatusActivity.class)));

  }

  private void intiUI() {
    EditText customerId = findViewById(R.id.customer_id);
    String providerName = intent.getStringExtra(ProjectConstants.PROVIDER);
    if (providerName != null) {
      ToolBarUtils.setTitle(this, providerName);
      ((TextView) findViewById(R.id.provider))
          .setText(providerName);
    }

    int imageId = intent.getIntExtra(ProjectConstants.PROVIDER_IMAGE, 0);
    if (imageId != 0) {
      ((ImageView) findViewById(R.id.providerImage))
          .setImageDrawable(ContextCompat.getDrawable(this, imageId));
    }
    String customerIdHint = intent.getStringExtra(ProjectConstants.CUSTOMER_ID_HINT);
    if (customerIdHint != null) {
      customerId.setHint(customerIdHint);
    }
    String customerIdMessage = intent.getStringExtra(ProjectConstants.CUSTOMER_ID_MESSAGE);
    TextView customerIdMessageView = findViewById(R.id.customer_id_message);
    if (customerIdMessage != null) {
      customerIdMessageView.setText(customerIdMessage);
    } else {
      customerIdMessageView.setVisibility(View.GONE);
    }

    String amountMessage = intent.getStringExtra(ProjectConstants.AMOUNT_MESSAGE);
    TextView amountMessageView = findViewById(R.id.amount_message);
    if (amountMessage != null) {
      amountMessageView.setText(amountMessage);
    } else {
      amountMessageView.setVisibility(View.GONE);
    }

    if (intent.getBooleanExtra(ProjectConstants.ACCEPT_ACCOUNT_ID, false)) {
      findViewById(R.id.account_number_layout).setVisibility(View.VISIBLE);
      String accountMessage = intent.getStringExtra(ProjectConstants.ACCOUNT_MESSAGE);
      TextView accountMessageView = findViewById(R.id.account_number_message);
      if (accountMessage != null) {
        accountMessageView.setText(accountMessage);
      } else {
        accountMessageView.setVisibility(View.GONE);
      }
    }


  }
}