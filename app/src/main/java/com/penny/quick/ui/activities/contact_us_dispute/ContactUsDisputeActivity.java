package com.penny.quick.ui.activities.contact_us_dispute;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.dispute_history.DisputeHistoryActivity;
import com.penny.quick.utils.NetworkConnectivityReceiver;
import com.penny.quick.utils.ToolBarUtils;

public class ContactUsDisputeActivity extends AppCompatActivity {

  private BroadcastReceiver mNetworkReceiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_us);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.contact_us));
    registerNetworkReceiver();
    if (getIntent().getBooleanExtra(ProjectConstants.IS_DISPUTE, false)) {
      ((TextView) findViewById(R.id.subject)).setText(R.string.transaction_id);
      findViewById(R.id.save).setOnClickListener(view -> startActivity(
          new Intent(ContactUsDisputeActivity.this, DisputeHistoryActivity.class)));
    }
  }

  protected void registerNetworkReceiver() {
    mNetworkReceiver = new NetworkConnectivityReceiver();
    IntentFilter filter = new IntentFilter();
    filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(mNetworkReceiver, filter);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unregisterReceiver(mNetworkReceiver);
  }
}