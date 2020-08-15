package com.penny.quick.ui.activities.recent_recharge;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.penny.database.dao.RecentRecharge;
import com.penny.quick.R;
import com.penny.quick.ui.adapters.RecentRechargeAdapter;
import com.penny.quick.ui.activities.recent_recharge.MonthBottomSheetDialog.BottomSheetListener;
import com.penny.quick.utils.ToolBarUtils;
import java.util.ArrayList;
import java.util.List;

public class RecentRechargeActivity extends AppCompatActivity implements BottomSheetListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recent_recharge);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.recent_recharge));

    RecyclerView recentRechargeList = findViewById(R.id.recent_recharges);
    recentRechargeList.setLayoutManager(new LinearLayoutManager(this));
    RecentRechargeAdapter recentRechargeAdapter = new RecentRechargeAdapter(
        generateRecentRechargeList(),
        this);
    recentRechargeList.setAdapter(recentRechargeAdapter);
    findViewById(R.id.month).setOnClickListener(view -> {
      MonthBottomSheetDialog monthBottomSheetDialog = new MonthBottomSheetDialog();
      monthBottomSheetDialog.show(getSupportFragmentManager(), "Month");
    });
  }

  private List<RecentRecharge> generateRecentRechargeList() {
    List<RecentRecharge> recentRecharges = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      RecentRecharge recentRecharge = new RecentRecharge();
      recentRecharge
          .setCompanyType(getString((i % 2 == 0) ? R.string.dummy_company_type : R.string.dish_tv));
      recentRecharge.setCustomerId(getString(R.string.dummy_mob_no));
      recentRecharge.setTransactionId(getString(R.string.dummy_trans_id));
      recentRecharge.setAmount((i % 2 == 0) ? 200.00f : 300.00f);
      recentRecharge.setStatus(i % 3);
      recentRecharge.setDate(getString(R.string.transaction_dummy_date));

      recentRecharges.add(recentRecharge);
    }
    return recentRecharges;
  }

  @Override
  public void onButtonClick(String text) {
    ((EditText)findViewById(R.id.month)).setText(text);
  }
}