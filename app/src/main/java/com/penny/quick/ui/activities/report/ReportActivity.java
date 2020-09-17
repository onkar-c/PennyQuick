package com.penny.quick.ui.activities.report;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.penny.database.entities.Report;
import com.penny.quick.R;
import com.penny.quick.ui.adapters.ReportsAdapter;
import com.penny.quick.models.BottomSheetCheckBox;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeBottomSheetDialog;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeBottomSheetDialog.BottomSheetListener;
import com.penny.quick.utils.ToolBarUtils;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity implements BottomSheetListener {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.reports));
    RecyclerView reportList = findViewById(R.id.reports_list);
    reportList.setLayoutManager(new LinearLayoutManager(this));
    ReportsAdapter reportsAdapter = new ReportsAdapter(generateReportList(), this);
    reportList.setAdapter(reportsAdapter);

    findViewById(R.id.month).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          generateMonthList(), getString(R.string.choose_month));
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });

    findViewById(R.id.category).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          getCategoriesFilter(), getString(R.string.categories));
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });

    findViewById(R.id.filters).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          getFilter(), getString(R.string.filter));
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });
  }

  private List<BottomSheetCheckBox> generateMonthList() {
    DateFormatSymbols dfs = new DateFormatSymbols();
    List<BottomSheetCheckBox> bottomSheetCheckBoxes = new ArrayList<>();
    String[] months = dfs.getMonths();
    for (String month : months) {
      BottomSheetCheckBox bottomSheetCheckBox = new BottomSheetCheckBox();
      bottomSheetCheckBox.setTitle(month);
      bottomSheetCheckBoxes.add(bottomSheetCheckBox);
    }
    return bottomSheetCheckBoxes;
  }

  private List<BottomSheetCheckBox> getCategoriesFilter() {

    List<BottomSheetCheckBox> bottomSheetCheckBoxes = new ArrayList<>();
    BottomSheetCheckBox bottomSheetCheckBox1 = new BottomSheetCheckBox();
    bottomSheetCheckBox1.setTitle(getString(R.string.mobile_recharge));
    bottomSheetCheckBoxes.add(bottomSheetCheckBox1);

    BottomSheetCheckBox bottomSheetCheckBox2 = new BottomSheetCheckBox();
    bottomSheetCheckBox2.setTitle(getString(R.string.dth));
    bottomSheetCheckBoxes.add(bottomSheetCheckBox2);

    BottomSheetCheckBox bottomSheetCheckBox3 = new BottomSheetCheckBox();
    bottomSheetCheckBox3.setTitle(getString(R.string.money_transfer));
    bottomSheetCheckBoxes.add(bottomSheetCheckBox3);

    return bottomSheetCheckBoxes;
  }

  private List<BottomSheetCheckBox> getFilter() {

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
  }


  private List<Report> generateReportList() {
    List<Report> reports = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Report report = new Report();
      report.setReportType(
          getString((i % 2 == 0) ? R.string.ledger_report : R.string.dispute_report));
      report.setTransactionType(getString((i % 2 == 0) ? R.string.credit : R.string.debit));
      report.setTransactionAmount((i % 2 == 0) ? 200.00f : 300.00f);
      report.setBalance((i % 2 == 0) ? 2000.00f : 3000.00f);
      report.setDate(getString(R.string.transaction_dummy_date));
      report
          .setDescription(getString((i % 2 == 0) ? R.string.dummy_company_type : R.string.dish_tv));
      reports.add(report);
    }
    return reports;
  }

  @Override
  public void onButtonClick(String text) {

  }
}