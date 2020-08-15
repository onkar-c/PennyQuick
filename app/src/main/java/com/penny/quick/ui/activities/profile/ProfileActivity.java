package com.penny.quick.ui.activities.profile;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.change_password.ChangePasswordActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeActivity;
import com.penny.quick.ui.activities.report.ReportActivity;
import com.penny.quick.ui.activities.web_view.WebViewActivity;

public class ProfileActivity extends BaseActivity implements OnClickListener {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    setUpToolBar();
    setToolBarDesign();

    findViewById(R.id.tv_change_pwd).setOnClickListener(this);
    findViewById(R.id.tv_recent_rechanges).setOnClickListener(this);
    findViewById(R.id.tv_reports).setOnClickListener(this);
    findViewById(R.id.tv_privacy_policy).setOnClickListener(this);
    findViewById(R.id.tv_logout).setOnClickListener(this);
  }

  void setToolBarDesign(){
    setTitle("Profile");
    toolbar.setBackgroundColor(ContextCompat.getColor(this,android.R.color.transparent));
    toolbar.setTitleTextColor(ContextCompat.getColor(this,android.R.color.white));
    if(toolbar.getNavigationIcon() !=null) {
      toolbar.getNavigationIcon()
          .setColorFilter(ContextCompat.getColor(this, android.R.color.white),
              PorterDuff.Mode.SRC_ATOP);
    }
  }

  @Override
  public void onClick(View view) {
    Intent intent = null;
    switch (view.getId()){
      case R.id.tv_change_pwd:
        intent = new Intent(ProfileActivity.this, ChangePasswordActivity.class);
        break;
      case R.id.tv_recent_rechanges:
        intent = new Intent(ProfileActivity.this, RecentRechargeActivity.class);
        break;
      case R.id.tv_reports:
        intent = new Intent(ProfileActivity.this, ReportActivity.class);
        break;
      case R.id.tv_privacy_policy:
        intent = new Intent(ProfileActivity.this, WebViewActivity.class);
        intent.putExtra(ProjectConstants.TITLE, R.string.privacy_policy);
        break;
      case R.id.tv_logout:
        break;
    }
    if (intent != null) {
      startActivity(intent);
    }
  }
}
