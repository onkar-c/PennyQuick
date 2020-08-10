package com.penny.quick.ui.activities.providersList;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;

public class ProvidersListActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_providers_list);
    setUpToolBar();
    setTitle(getString(R.string.select_provider));
    GridLayout dthGrid = (GridLayout) findViewById(R.id.dthGrid);
    GridLayout landLineGrid = (GridLayout) findViewById(R.id.landLineGrid);
    boolean isDth = getIntent().getBooleanExtra(ProjectConstants.IS_DTH, true);
    dthGrid.setVisibility(isDth ? View.VISIBLE : View.GONE);
    landLineGrid.setVisibility(isDth ? View.GONE : View.VISIBLE);
  }
}