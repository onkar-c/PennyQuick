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
import androidx.work.WorkInfo.State;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.penny.core.APITags;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.User;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivity;
import com.penny.quick.ui.activities.dispute_history.DisputeHistoryActivity;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivity;
import com.penny.quick.ui.activities.money_transfer.MoneyTransferNumberActivity;
import com.penny.quick.ui.activities.profile.ProfileActivity;
import com.penny.quick.ui.activities.providersList.ProvidersListActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeActivity;
import com.penny.quick.ui.activities.report.ReportActivity;
import com.penny.quick.ui.activities.web_view.WebViewActivity;
import com.penny.quick.utils.NetworkConnectivityReceiver.NetworkConnectivityChangeListener;
import javax.inject.Inject;

public class DashBoardActivity extends BaseActivity implements NetworkConnectivityChangeListener {

  @Inject
  DashBoardActivityViewModel dashBoardActivityViewModel;
  private DrawerLayout drawer;
  private TextView walletBalance, drawerUserNameTV;
  private ImageView profileIV, drawerProfileIV;
  private TextView networkWarningTV;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dash_board);
    setToolBarAndNavigationDrawer();
    initUi();
    registerNetworkReceiver();
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
    networkWarningTV = findViewById(R.id.tv_network_err);
    findViewById(R.id.iv_sync).setOnClickListener(view -> getUserDetails(true));
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
      ((TextView) findViewById(R.id.tv_user_name)).setText(user.getBusinessName());
      drawerUserNameTV.setText(user.getBusinessName());
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (NetworkUtils.isConnected(this)) {
      manageNetworkErr(NetworkUtils.isConnected(this));
      getUserDetails(false);
    }
  }

  private void getUserDetails(boolean isBalanceRequest) {
    if (NetworkUtils.isConnected(this)) {
      dashBoardActivityViewModel.getUserInfo(isBalanceRequest)
          .observe(this, workInfo -> userInfoObserver(workInfo, isBalanceRequest));
    } else if (isBalanceRequest) {
      toast(APITags.DEVICE_IS_OFFLINE);
    }
  }

  private void userInfoObserver(WorkInfo workInfo, boolean isBalanceRequest) {
    if (workInfo != null && (isBalanceRequest || workInfo.getState() == State.FAILED)) {
      apiResponseHandler(workInfo);
    }
  }

  @SuppressLint("RtlHardcoded")
  private void setToolBarAndNavigationDrawer() {
    Toolbar toolbar = findViewById(R.id.dashBoardToolBar);
    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    View navigationHeader = navigationView.getHeaderView(0);
    drawerProfileIV = navigationHeader.findViewById(R.id.iv_user_drawer);
    drawerUserNameTV = navigationHeader.findViewById(R.id.userName);

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
      intent = new Intent(DashBoardActivity.this, ContactUsDisputeActivity.class);
      intent.putExtra(ProjectConstants.IS_DISPUTE, true);
//      intent = new Intent(DashBoardActivity.this, ProvidersListActivity.class);
//      intent.putExtra(ProjectConstants.IS_DTH, false);
    } else if (view.getId() == R.id.bt_prepaid || view.getId() == R.id.bt_postPaid) {
      intent = new Intent(DashBoardActivity.this, MobileRechargeActivity.class);
      intent.putExtra(ProjectConstants.TYPE, view.getId() == R.id.bt_prepaid);
    } else if (view.getId() == R.id.addMoney) {
      showMessageDialog(null, getString(R.string.add_money_error));
      return;
    } else if (view.getId() == R.id.moneyTransfer) {
      intent = new Intent(DashBoardActivity.this, MoneyTransferNumberActivity.class);
//      showMessageDialog(null, getString(R.string.money_transfer_error));
//      return;
    }
    if (intent != null) {
      startActivity(intent);
    } else {
      Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
  }

  private void handelCommonGridClick(View view) {
    Intent intent = null;
    int id = view.getId();
    if (id == R.id.reports) {
      intent = new Intent(DashBoardActivity.this, ReportActivity.class);
    } else if (id == R.id.contact_support) {
      intent = new Intent(DashBoardActivity.this, ContactUsDisputeActivity.class);
    } else if (id == R.id.recent_recharge) {
      intent = new Intent(DashBoardActivity.this, RecentRechargeActivity.class);
    } else if (id == R.id.user_profile) {
      intent = new Intent(DashBoardActivity.this, ProfileActivity.class);
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
      int itemId = item.getItemId();
      if (itemId == R.id.home) {
        intent = null;
      } else if (itemId == R.id.contactUs) {
        intent = new Intent(DashBoardActivity.this, ContactUsDisputeActivity.class);
      } else if (itemId == R.id.profile) {
        intent = new Intent(DashBoardActivity.this, ProfileActivity.class);
      } else if (itemId == R.id.faq || itemId == R.id.termsCondition
          || itemId == R.id.privacyPolicy) {
        intent = new Intent(DashBoardActivity.this, WebViewActivity.class);
        intent.putExtra(ProjectConstants.URL, itemId == R.id.faq ? ProjectConstants.FAQ_URL
            : itemId == R.id.termsCondition ? ProjectConstants.TERMS_AND_CONDITION_URL
                : ProjectConstants.PRIVACY_POLICY_URL);
        intent.putExtra(ProjectConstants.TITLE, item.getTitle());
      } else if (itemId == R.id.recentTransaction) {
        intent = new Intent(DashBoardActivity.this, RecentRechargeActivity.class);
      } else if (itemId == R.id.logout) {
        performLogout();
      } else if (itemId == R.id.disputeHistory) {
        intent = new Intent(DashBoardActivity.this, DisputeHistoryActivity.class);
      } else if (itemId == R.id.add_money) {
        showMessageDialog(null, getString(R.string.add_money_error));
        return true;
      } else {
        Toast.makeText(DashBoardActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
      }
      if (intent != null) {
        startActivity(intent);
      }

      drawer.close();
      return true;
    };
  }

  public void manageNetworkErr(boolean isNetworkAvailable) {
    showWarningText(networkWarningTV, isNetworkAvailable);
  }

  @Override
  public void networkStatusChange(boolean status) {
    manageNetworkErr(status);
  }
}
