package com.penny.quick.ui.activities.dash_board;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener;
import com.penny.quick.R;

public class DashBoardActivity extends AppCompatActivity {

  private DrawerLayout drawer;
  @SuppressLint("RtlHardcoded")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_dash_board);
    Toolbar toolbar = findViewById(R.id.toolbar);
    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(getNavigationItemClickListener());
    navigationView.bringToFront();
    setSupportActionBar(toolbar);
    toolbar.setNavigationOnClickListener(
        view -> {
          if (!drawer.isDrawerOpen(Gravity.LEFT)) drawer.open();
        });
  }

  private OnNavigationItemSelectedListener getNavigationItemClickListener() {
    return item -> {
      Toast.makeText(DashBoardActivity.this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
      drawer.close();
      return true;
    };
  }

}
