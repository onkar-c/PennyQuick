package com.penny.quick.ui.activities.report;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import com.penny.core.models.DateFormatModel;
import com.penny.core.util.NetworkUtils;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetCheckBox;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeBottomSheetDialog;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeBottomSheetDialog.BottomSheetListener;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargesActivityViewModel;
import com.penny.quick.ui.adapters.ReportsAdapter;
import com.penny.quick.utils.ToolBarUtils;
import com.penny.quick.utils.UiUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;

public class ReportActivity extends BaseActivity {

  private final List<DateFormatModel> dateFormatModels = new ArrayList<>();
  private final List<BottomSheetCheckBox> bottomSheetCategoriesCheckBoxes = new ArrayList<>();
  @Inject
  RecentRechargesActivityViewModel recentRechargesActivityViewModel;

  private final BottomSheetListener dateBottomSheet = bottomSheetCheckBoxes -> {
    for (DateFormatModel dateFormatModel : dateFormatModels) {
      dateFormatModel.setChecked(false);
      if (bottomSheetCheckBoxes != null && bottomSheetCheckBoxes.size() > 0) {
        for (BottomSheetCheckBox bottomSheetCheckBox : bottomSheetCheckBoxes) {
          if (dateFormatModel.getId().equals(bottomSheetCheckBox.getId())) {
            dateFormatModel.setChecked(true);
            break;
          }
        }
      }
    }
    getReports();
  };
  private final BottomSheetListener categoryBottomSheet = bottomSheetCheckBoxes -> {
    for (BottomSheetCheckBox categoryFormatModel : bottomSheetCategoriesCheckBoxes) {
      categoryFormatModel.setChecked(false);
      if (bottomSheetCheckBoxes != null && bottomSheetCheckBoxes.size() > 0) {
        for (BottomSheetCheckBox bottomSheetCheckBox : bottomSheetCheckBoxes) {
          if (categoryFormatModel.getId().equals(bottomSheetCheckBox.getId())) {
            categoryFormatModel.setChecked(true);
            break;
          }
        }
      }
    }
    getReports();
  };
  private BroadcastReceiver mNetworkReceiver;

  private void getReports() {
    if (NetworkUtils.isConnected(this)) {
      recentRechargesActivityViewModel
          .getReportsFromServer(dateFormatModels, bottomSheetCategoriesCheckBoxes).observe(this,
          this::observeReports);
    }
  }

  private void observeReports(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.reports));
    RecyclerView reportList = findViewById(R.id.reports_list);
    dateFormatModels.addAll(UiUtils.generateDates());
    getCategoriesFilter();
    reportList.setLayoutManager(new LinearLayoutManager(this));
    ReportsAdapter reportsAdapter = new ReportsAdapter(new ArrayList<>(), this);
    reportList.setAdapter(reportsAdapter);

    findViewById(R.id.month).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          generateMonthList(), getString(R.string.choose_month), dateBottomSheet);
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });

    findViewById(R.id.category).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          bottomSheetCategoriesCheckBoxes, getString(R.string.categories), categoryBottomSheet);
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });

    /*findViewById(R.id.filters).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          getFilter(), getString(R.string.filter), filterBottomSheet);
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });*/

    recentRechargesActivityViewModel.getReports().observe(this, reportsAdapter::setList);
  }

  private List<BottomSheetCheckBox> generateMonthList() {

    List<BottomSheetCheckBox> bottomSheetCheckBoxes = new ArrayList<>();
    for (DateFormatModel dateFormatModel : dateFormatModels) {
      BottomSheetCheckBox bottomSheetCheckBox = new BottomSheetCheckBox();
      bottomSheetCheckBox
          .setTitle(dateFormatModel.getMonthDisplay() + " - " + dateFormatModel.getYear());
      bottomSheetCheckBox.setId(dateFormatModel.getId());
      bottomSheetCheckBoxes.add(bottomSheetCheckBox);
      bottomSheetCheckBox.setChecked(dateFormatModel.isChecked());
    }

    return bottomSheetCheckBoxes;
  }

  private void getCategoriesFilter() {

    BottomSheetCheckBox bottomSheetCheckBox1 = new BottomSheetCheckBox();
    bottomSheetCheckBox1.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox1.setActualName("BalanceUpdate");
    bottomSheetCheckBox1.setTitle("Balance Update");
    bottomSheetCategoriesCheckBoxes.add(bottomSheetCheckBox1);

    BottomSheetCheckBox bottomSheetCheckBox2 = new BottomSheetCheckBox();
    bottomSheetCheckBox2.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox2.setActualName("PAYMENT");
    bottomSheetCheckBox2.setTitle("Payment");
    bottomSheetCategoriesCheckBoxes.add(bottomSheetCheckBox2);

    BottomSheetCheckBox bottomSheetCheckBox3 = new BottomSheetCheckBox();
    bottomSheetCheckBox3.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox3.setActualName("Recharge");
    bottomSheetCheckBox3.setTitle("Recharge");
    bottomSheetCategoriesCheckBoxes.add(bottomSheetCheckBox3);

    BottomSheetCheckBox bottomSheetCheckBox4 = new BottomSheetCheckBox();
    bottomSheetCheckBox4.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox4.setActualName("Money Transfer");
    bottomSheetCheckBox4.setTitle("Money Transfer");
    bottomSheetCategoriesCheckBoxes.add(bottomSheetCheckBox4);


  }

  /*private List<BottomSheetCheckBox> getFilter() {

    List<BottomSheetCheckBox> bottomSheetCheckBoxes = new ArrayList<>();
    BottomSheetCheckBox bottomSheetCheckBox1 = new BottomSheetCheckBox();
    bottomSheetCheckBox1.setTitle(getString(R.string.pending));
    bottomSheetCheckBoxes.add(bottomSheetCheckBox1);

    BottomSheetCheckBox bottomSheetCheckBox2 = new BottomSheetCheckBox();
    bottomSheetCheckBox2.setTitle(getString(R.string.failed));
    bottomSheetCheckBoxes.add(bottomSheetCheckBox2);

    BottomSheetCheckBox bottomSheetCheckBox3 = new BottomSheetCheckBox();
    bottomSheetCheckBox3.setTitle(getString(R.string.success));
    bottomSheetCheckBoxes.add(bottomSheetCheckBox3);

    return bottomSheetCheckBoxes;
  }*/


  @Override
  protected void onResume() {
    super.onResume();
    if (!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
    getReports();
  }
}