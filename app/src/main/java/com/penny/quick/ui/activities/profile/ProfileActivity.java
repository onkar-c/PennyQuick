package com.penny.quick.ui.activities.profile;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.User;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.change_password.ChangePasswordActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeActivity;
import com.penny.quick.ui.activities.report.ReportActivity;
import com.penny.quick.ui.activities.web_view.WebViewActivity;
import javax.inject.Inject;

public class ProfileActivity extends BaseActivity implements OnClickListener {

  @Inject
  ProfileActivityViewModel profileActivityViewModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    setUpToolBar();
    setToolBarDesign();

    findViewById(R.id.tv_change_pwd).setOnClickListener(this);
    findViewById(R.id.tv_recent_recharges).setOnClickListener(this);
    findViewById(R.id.tv_reports).setOnClickListener(this);
    findViewById(R.id.tv_privacy_policy).setOnClickListener(this);
    findViewById(R.id.tv_logout).setOnClickListener(this);
    getProfileData();
  }

  private void getProfileData() {
    profileActivityViewModel.getProfileData().observe(this, this::setProfileData);
  }

  private void setProfileData(User user) {
    if (user != null) {
      ImageView profileIV = findViewById(R.id.iv_profile);
      showProfileImage(user.getImageUrl(), profileIV);
      ((TextView) findViewById(R.id.tv_profile_name)).setText(user.getBusinessName());
      ((TextView) findViewById(R.id.tv_profile_mob_no)).setText(user.getMobileNumber());
      ((TextView) findViewById(R.id.tv_profile_email)).setText(user.getEmail());
//    ((TextView) findViewById(R.id.tv_profile_address)).setText(user.getAddress());
      ((TextView) findViewById(R.id.tv_profile_wallet_balance)).setText(user.getTotalBalance());
    }
  }


  void setToolBarDesign() {
    setTitle("Profile");
    toolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
    toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
    if (toolbar.getNavigationIcon() != null) {
      toolbar.getNavigationIcon()
          .setColorFilter(ContextCompat.getColor(this, android.R.color.white),
              PorterDuff.Mode.SRC_ATOP);
    }
  }

  @Override
  public void onClick(View view) {
    Intent intent = null;
    int id = view.getId();
    if (id == R.id.tv_change_pwd) {
      intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
    } else if (id == R.id.tv_recent_recharges) {
      intent = new Intent(ProfileActivity.this, RecentRechargeActivity.class);
    } else if (id == R.id.tv_reports) {
      intent = new Intent(ProfileActivity.this, ReportActivity.class);
    } else if (id == R.id.tv_privacy_policy) {
      intent = new Intent(ProfileActivity.this, WebViewActivity.class);
      intent.putExtra(ProjectConstants.URL, ProjectConstants.PRIVACY_POLICY_URL);
      intent.putExtra(ProjectConstants.TITLE, getString(R.string.privacy_policy));
    } else if (id == R.id.tv_logout) {
      performLogout();
      return;
    }
    if (intent != null) {
      startActivity(intent);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
  }
}
