package com.penny.quick.ui.activities.dispute_history;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.util.NetworkUtils;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeViewModel;
import com.penny.quick.ui.adapters.DisputeReportsAdapter;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class DisputeHistoryActivity extends BaseActivity {

  @Inject
  ContactUsDisputeViewModel contactUsDisputeViewModel;
  private RecyclerView reportList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dispute_history);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.dispute_report));
    registerNetworkReceiver();
    setDisputeHistoryObserver();
    reportList = findViewById(R.id.dispute_reports);

  }

  private void setDisputeHistoryObserver() {
    contactUsDisputeViewModel.getDisputeHistoryObserver().observe(this, disputes -> {
      reportList.setLayoutManager(new LinearLayoutManager(this));
      DisputeReportsAdapter reportsAdapter = new DisputeReportsAdapter(disputes);
      reportList.setAdapter(reportsAdapter);
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    getDisputeHistory();
  }

  private void getDisputeHistory() {
    if (NetworkUtils.isConnected(this)) {
      contactUsDisputeViewModel
          .getDisputeHistory().observe(this,
          this::observeSendDataApi);
    } else {
      toast(APITags.DEVICE_IS_OFFLINE);
    }
  }

  private void observeSendDataApi(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
    }
  }

}