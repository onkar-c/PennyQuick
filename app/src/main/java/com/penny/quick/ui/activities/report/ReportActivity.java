package com.penny.quick.ui.activities.report;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import com.penny.database.dao.Report;
import com.penny.quick.R;
import com.penny.quick.adapters.ReportsAdapter;
import com.penny.quick.utils.ToolBarUtils;
import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {


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
  }

  private List<Report> generateReportList() {
    List<Report> reports = new ArrayList<>();
    for(int i = 0; i < 10; i++) {
      Report report = new Report();
      report.setReportType(getString((i % 2 == 0) ? R.string.ledger_report : R.string.dispute_report));
      report.setTransactionType(getString((i % 2 == 0) ? R.string.credit : R.string.debit));
      report.setTransactionAmount((i % 2 == 0) ? 200.00f : 300.00f);
      report.setBalance((i % 2 == 0) ? 2000.00f : 3000.00f);
      report.setDate(getString(R.string.transaction_dummy_date));
      report.setDescription(getString((i % 2 == 0) ? R.string.dummy_company_type : R.string.dish_tv));
      reports.add(report);
    }
    return reports;
  }
}