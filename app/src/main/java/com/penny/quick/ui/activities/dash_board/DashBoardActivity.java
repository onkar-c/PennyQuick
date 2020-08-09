package com.penny.quick.ui.activities.dash_board;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivity;
import com.penny.quick.ui.activities.providersList.ProvidersListActivity;

public class DashBoardActivity extends AppCompatActivity {

  private DrawerLayout drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dash_board);
    setToolBarAndNavigationDrawer();

    GridLayout grid = (GridLayout) findViewById(R.id.rechargeGridLayout);
    for (int i = 0; i < grid.getChildCount(); i++) {
      ((CardView) grid.getChildAt(i)).setOnClickListener(this::handelRechargeGridClick);
    }
  }

  @SuppressLint("RtlHardcoded")
  private void setToolBarAndNavigationDrawer() {
    Toolbar toolbar = findViewById(R.id.dashBoardToolBar);
    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
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
    } else if(view.getId() == R.id.bt_prepaid || view.getId() == R.id.bt_postPaid ) {
      intent = new Intent(DashBoardActivity.this, MobileRechargeActivity.class);
    }
    if (intent != null) {
      startActivity(intent);
    }
  }

  private OnNavigationItemSelectedListener getNavigationItemClickListener() {
    return item -> {
      Toast.makeText(DashBoardActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
      drawer.close();
      return true;
    };
  }
}
