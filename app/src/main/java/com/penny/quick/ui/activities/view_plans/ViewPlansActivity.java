package com.penny.quick.ui.activities.view_plans;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.adapters.TabLayoutViewPager;
import com.penny.quick.models.PlanModel;
import com.penny.quick.models.TabModel;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.adapters.PlansAdapter.PlanClickListener;
import com.penny.quick.utils.ToolBarUtils;
import java.util.ArrayList;
import java.util.List;

public class ViewPlansActivity extends BaseActivity implements PlanClickListener {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_plans);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.plans));
    registerNetworkReceiver();
    TabLayout tabLayout = findViewById(R.id.tl_plans);
    ViewPager2 viewPager = findViewById(R.id.vp_plans);
    List<TabModel> tabModels = getTabModels();

    TabLayoutViewPager tabLayoutViewPager = new TabLayoutViewPager(tabModels, this);
    viewPager.setAdapter(tabLayoutViewPager);
    new TabLayoutMediator(tabLayout, viewPager,
        (tab, position) -> tab.setText(tabModels.get(position).getTitle())).attach();
  }

  private List<TabModel> getTabModels() {
    List<TabModel> tabModels = new ArrayList<>();
    TabModel tabModel = new TabModel();
    tabModel.setTitle("Popular");
    tabModel.setPlanModels(generatePlans(5));
    tabModels.add(tabModel);

    TabModel tabModel1 = new TabModel();
    tabModel1.setTitle("Jio Phone");
    tabModel1.setPlanModels(generatePlans(3));
    tabModels.add(tabModel1);

    TabModel tabModel2 = new TabModel();
    tabModel2.setTitle("All Plans");
    tabModel2.setPlanModels(generatePlans(10));
    tabModels.add(tabModel2);

    TabModel tabModel3 = new TabModel();
    tabModel3.setTitle("Top Up");
    tabModel3.setPlanModels(generatePlans(1));
    tabModels.add(tabModel3);

    TabModel tabModel4 = new TabModel();
    tabModel4.setTitle("Data Added");
    tabModel4.setPlanModels(generatePlans(3));
    tabModels.add(tabModel4);
    return tabModels;
  }

  private List<PlanModel> generatePlans(int count) {
    List<PlanModel> planModels = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      PlanModel planModel = new PlanModel();
      planModel.setTalktime(0F);
      planModel.setData(getString(R.string.dummy_data_string));
      planModel.setValidity(getString(R.string.dummy_validity));
      planModel.setAmount(400F);
      planModel.setMessage1(getString(R.string.dummy_message_1));
      planModel.setMessage2(getString(R.string.dummy_message_2));
      planModel.setMessage3(getString(R.string.dummy_message_3));
      planModels.add(planModel);
    }
    return planModels;
  }

  @Override
  public void onPlanClick(PlanModel plan) {
    Log.e("Plan", plan.getMessage1());
    Intent intent = new Intent();
    intent.putExtra(ProjectConstants.PLAN, plan);
    setResult(RESULT_OK, intent);
    finish();
  }

  @Override
  protected void onResume() {
    super.onResume();
    if(!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
  }
}
