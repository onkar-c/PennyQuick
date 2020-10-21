package com.penny.quick.ui.activities.money_transfer;

import android.os.Bundle;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class AddRecepientActivity extends BaseActivity {

  @Inject
  MoneyTransferActivityViewModel moneyTransferActivityViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_recepient);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.add_recepient));
  }
}