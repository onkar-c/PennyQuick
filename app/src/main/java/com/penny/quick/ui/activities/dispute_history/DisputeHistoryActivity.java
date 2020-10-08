package com.penny.quick.ui.activities.dispute_history;

import static com.penny.quick.ui.activities.BaseActivity.manageBaseNetworkErr;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.penny.core.util.NetworkUtils;
import com.penny.database.entities.Report;
import com.penny.quick.R;
import com.penny.quick.ui.adapters.DisputeReportsAdapter;
import com.penny.quick.utils.NetworkConnectivityReceiver;
import com.penny.quick.utils.ToolBarUtils;
import java.util.ArrayList;
import java.util.List;

public class DisputeHistoryActivity extends AppCompatActivity {

  private BroadcastReceiver mNetworkReceiver;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dispute_history);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.dispute_report));
    registerNetworkReceiver();
    RecyclerView reportList = findViewById(R.id.dispute_reports);
    reportList.setLayoutManager(new LinearLayoutManager(this));
    DisputeReportsAdapter reportsAdapter = new DisputeReportsAdapter(generateReportList());
    reportList.setAdapter(reportsAdapter);
  }

  private List<Report> generateReportList() {
    List<Report> reports = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Report report = new Report();
      report.setTransaction_id(getString(R.string.dummy_trans_id));
      report.setDate(getString(R.string.transaction_dummy_date));
      report
          .setDescription(getString((i % 2 == 0) ? R.string.dummy_company_type : R.string.dish_tv));
      reports.add(report);
    }
    return reports;
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

  @Override
  protected void onResume() {
    super.onResume();
    if(!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
  }
}