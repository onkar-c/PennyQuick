package com.penny.quick.ui.activities.recent_recharge;

import android.os.Bundle;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import com.penny.core.models.DateFormatModel;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetCheckBox;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeBottomSheetDialog.BottomSheetListener;
import com.penny.quick.ui.adapters.RecentRechargeAdapter;
import com.penny.quick.utils.ToolBarUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.inject.Inject;

public class RecentRechargeActivity extends BaseActivity {

  @Inject
  RecentRechargesActivityViewModel recentRechargesActivityViewModel;
  List<BottomSheetCheckBox> bottomSheetCategoriesCheckBoxes = new ArrayList<>();
  List<BottomSheetCheckBox> statusCheckBoxes = new ArrayList<>();
  private RecentRechargeAdapter recentRechargeAdapter;
  private List<DateFormatModel> dateFormatModels = new ArrayList<>();
  private BottomSheetListener dateBottomSheet = bottomSheetCheckBoxes -> {
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
    getRecentRechargesFromServer();
  };
  private BottomSheetListener categoryBottomSheet = bottomSheetCheckBoxes -> {
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
    getRecentRechargesFromServer();
  };
  private BottomSheetListener filterBottomSheet = bottomSheetCheckBoxes -> {
    for (BottomSheetCheckBox categoryFormatModel : statusCheckBoxes) {
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
    getRecentRechargesFromServer();
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recent_recharge);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.recent_recharge));
    generateDates();
    getCategoriesFilter();
    getFilter();
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
          generateMonthList(), getString(R.string.choose_month), dateBottomSheet);
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });

    findViewById(R.id.category).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          bottomSheetCategoriesCheckBoxes, getString(R.string.categories), categoryBottomSheet);
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });

    findViewById(R.id.filters).setOnClickListener(view -> {
      RecentRechargeBottomSheetDialog recentRechargeBottomSheetDialog = new RecentRechargeBottomSheetDialog(
          statusCheckBoxes, getString(R.string.filter), filterBottomSheet);
      recentRechargeBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });
  }

  private void generateDates() {
    Calendar cal = Calendar.getInstance();
    for (int j = 0; j < 2; j++) {
      for (int i = j == 0 ? cal.get(Calendar.MONTH) : 12; i > 0; i--) {
        cal.set(Calendar.MONTH, i);
        DateFormatModel dateFormatModel = new DateFormatModel();
        dateFormatModel.setId(UUID.randomUUID().toString());
        dateFormatModel
            .setMonthDisplay(
                cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        dateFormatModel.setMonth("" + (cal.get(Calendar.MONTH) + 1));
        dateFormatModel.setYear(String.valueOf(cal.get(Calendar.YEAR)));
        Log.d("date", dateFormatModel.getMonth() + " " + dateFormatModel.getYear() + " " + j);
        dateFormatModels.add(dateFormatModel);
      }
      cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - (j + 2));
    }
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
    bottomSheetCheckBox1.setTitle(getString(R.string.mobile_recharge));
    bottomSheetCheckBox1.setActualName("Mobile");
    bottomSheetCategoriesCheckBoxes.add(bottomSheetCheckBox1);

    BottomSheetCheckBox bottomSheetCheckBox2 = new BottomSheetCheckBox();
    bottomSheetCheckBox2.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox2.setTitle(getString(R.string.dth));
    bottomSheetCheckBox2.setActualName("DTH");
    bottomSheetCategoriesCheckBoxes.add(bottomSheetCheckBox2);

//    BottomSheetCheckBox bottomSheetCheckBox3 = new BottomSheetCheckBox();
//    bottomSheetCheckBox3.setTitle(getString(R.string.money_transfer));
//    bottomSheetCheckBoxes.add(bottomSheetCheckBox3);
  }

  private void getFilter() {
    BottomSheetCheckBox bottomSheetCheckBox1 = new BottomSheetCheckBox();
    bottomSheetCheckBox1.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox1.setTitle(getString(R.string.pending));
    bottomSheetCheckBox1.setActualName(ProjectConstants.PENDING);
    statusCheckBoxes.add(bottomSheetCheckBox1);

    BottomSheetCheckBox bottomSheetCheckBox2 = new BottomSheetCheckBox();
    bottomSheetCheckBox2.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox2.setTitle(getString(R.string.failed));
    bottomSheetCheckBox2.setActualName(ProjectConstants.FAILURE);
    statusCheckBoxes.add(bottomSheetCheckBox2);

    BottomSheetCheckBox bottomSheetCheckBox3 = new BottomSheetCheckBox();
    bottomSheetCheckBox3.setId(UUID.randomUUID().toString());
    bottomSheetCheckBox3.setTitle(getString(R.string.success));
    bottomSheetCheckBox3.setActualName(ProjectConstants.SUCCESS);
    statusCheckBoxes.add(bottomSheetCheckBox3);
  }

  private void getRecentRechargesFromServer() {
    recentRechargesActivityViewModel
        .getRecentRechargesFromServer(dateFormatModels, bottomSheetCategoriesCheckBoxes,
            statusCheckBoxes).observe(this,
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
}