package com.penny.quick.ui.activities.recent_recharge;

import android.os.Bundle;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetCheckBox;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeBottomSheetDialog.BottomSheetListener;
import com.penny.quick.ui.adapters.RecentRechargeAdapter;
import com.penny.quick.utils.ToolBarUtils;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class RecentRechargeActivity extends BaseActivity implements BottomSheetListener {

  @Inject
  RecentRechargesActivityViewModel recentRechargesActivityViewModel;
  private RecentRechargeAdapter recentRechargeAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recent_recharge);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.recent_recharge));
    initUi();
    setListeners();
    setData();
  }

  private void setData() {
    recentRechargesActivityViewModel.getRecentRecharges()
        .observe(this, recentRecharges -> recentRechargeAdapter.setList(recentRecharges));
  }

  private void initUi() {
    RecyclerView recentRechargeList = findViewById(R.id.recent_recharges);
    recentRechargeList.setLayoutManager(new LinearLayoutManager(this));
    recentRechargeAdapter = new RecentRechargeAdapter(
        new ArrayList<>(),
        this);
    recentRechargeList.setAdapter(recentRechargeAdapter);
  }

  private void setListeners() {
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

  private void getRecentRechargesFromServer() {
    recentRechargesActivityViewModel.getRecentRechargesFromServer().observe(this,
        this::recentRechargeApiSuccess);
  }

  private void recentRechargeApiSuccess(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    getRecentRechargesFromServer();
  }

  @Override
  public void onButtonClick(String text) {
    ((EditText) findViewById(R.id.month)).setText(text);
  }
}