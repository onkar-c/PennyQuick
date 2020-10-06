package com.penny.quick.ui.activities.dash_board;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.work.WorkInfo;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.User;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivity;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivity;
import com.penny.quick.ui.activities.profile.ProfileActivity;
import com.penny.quick.ui.activities.providersList.ProvidersListActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeActivity;
import com.penny.quick.ui.activities.report.ReportActivity;
import com.penny.quick.ui.activities.web_view.WebViewActivity;
import javax.inject.Inject;

public class DashBoardActivity extends BaseActivity {

  @Inject
  DashBoardActivityViewModel dashBoardActivityViewModel;
  private DrawerLayout drawer;
  private TextView walletBalance, userName, drawerUserName;
  private ImageView profileIV;
  private ImageView drawerProfileIV;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dash_board);
    setToolBarAndNavigationDrawer();
    initUi();

    GridLayout grid = findViewById(R.id.rechargeGridLayout);
    for (int i = 0; i < grid.getChildCount(); i++) {
      grid.getChildAt(i).setOnClickListener(this::handelRechargeGridClick);
    }

    GridLayout commonGrid = findViewById(R.id.commonGrid);
    for (int i = 0; i < commonGrid.getChildCount(); i++) {
      commonGrid.getChildAt(i).setOnClickListener(this::handelCommonGridClick);
    }
    setObservers();
  }

  private void initUi() {
    walletBalance = findViewById(R.id.walletBalance);
    profileIV = findViewById(R.id.imageView);
    userName = findViewById(R.id.tv_user_name);

  }

  private void setObservers() {
    dashBoardActivityViewModel.getUser().observe(this, this::setUserDetails);
  }

  private void setUserDetails(User user) {
    if (user != null) {
      walletBalance.setText(
          String.format("%s %s", getString(R.string.rupees_sign), user.getTotalBalance()));
      showProfileImage(user.getImageUrl(), profileIV);
      showProfileImage(user.getImageUrl(), drawerProfileIV);
      userName.setText(user.getBusinessName());
      drawerUserName.setText(user.getBusinessName());
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    dashBoardActivityViewModel.getUserInfo().observe(this, this::userInfoObserver);
  }

  private void userInfoObserver(WorkInfo workInfo) {
    if (workInfo != null) {
      apiResponseHandler(workInfo);
    }
  }

  @SuppressLint("RtlHardcoded")
  private void setToolBarAndNavigationDrawer() {
    Toolbar toolbar = findViewById(R.id.dashBoardToolBar);
    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    View header = navigationView.getHeaderView(0);
    drawerProfileIV = header.findViewById(R.id.iv_user_drawer);
    drawerUserName = header.findViewById(R.id.userName);
    navigationView.setNavigationItemSelectedListener(getNavigationItemClickListener());
    navigationView.bringToFront();
    setSupportActionBar(toolbar);
    toolbar.setNavigationOnClickListener(
        view -> {
          if (!drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.open();
          }
        });
  }

  private void handelRechargeGridClick(View view) {
    Intent intent = null;
    if (view.getId() == R.id.bt_dth) {
      intent = new Intent(DashBoardActivity.this, ProvidersListActivity.class);
      intent.putExtra(ProjectConstants.IS_DTH, true);
    } else if (view.getId() == R.id.bt_landLine) {
      intent = new Intent(DashBoardActivity.this, ProvidersListActivity.class);
      intent.putExtra(ProjectConstants.IS_DTH, false);
    } else if (view.getId() == R.id.bt_prepaid || view.getId() == R.id.bt_postPaid) {
      intent = new Intent(DashBoardActivity.this, MobileRechargeActivity.class);
      intent.putExtra(ProjectConstants.TYPE, view.getId() == R.id.bt_prepaid);
    }
    if (intent != null) {
      startActivity(intent);
    } else {
      Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
  }

  private void handelCommonGridClick(View view) {
    Intent intent = null;
    switch (view.getId()) {
      case R.id.reports:
        intent = new Intent(DashBoardActivity.this, ReportActivity.class);
        break;
      case R.id.contact_support:
        intent = new Intent(DashBoardActivity.this, ContactUsDisputeActivity.class);
        break;
      case R.id.recent_recharge:
        intent = new Intent(DashBoardActivity.this, RecentRechargeActivity.class);
        break;
      case R.id.user_profile:
        intent = new Intent(DashBoardActivity.this, ProfileActivity.class);
        break;
    }
    if (intent != null) {
      startActivity(intent);
    } else {
      Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
  }

  private OnNavigationItemSelectedListener getNavigationItemClickListener() {
    return item -> {
      Intent intent = null;
      switch (item.getItemId()) {
        case R.id.home:
          intent = null;
          break;
        case R.id.contactUs:
          intent = new Intent(DashBoardActivity.this, ContactUsDisputeActivity.class);
          break;
        case R.id.profile:
          intent = new Intent(DashBoardActivity.this, ProfileActivity.class);
          break;
        case R.id.faq:
        case R.id.termsCondition:
        case R.id.privacyPolicy:
          intent = new Intent(DashBoardActivity.this, WebViewActivity.class);
          intent.putExtra(ProjectConstants.TITLE, item.getTitle());
          break;
        case R.id.recentTransaction:
          intent = new Intent(DashBoardActivity.this, RecentRechargeActivity.class);
          break;
        case R.id.logout:
          performLogout();
          break;
        default:
          Toast.makeText(DashBoardActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
          break;
      }
      if (intent != null) {
        startActivity(intent);
      }

      drawer.close();
      return true;
    };
  }
}
