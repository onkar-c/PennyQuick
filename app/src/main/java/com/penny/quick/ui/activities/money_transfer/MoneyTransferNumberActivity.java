package com.penny.quick.ui.activities.money_transfer;

import android.content.Intent;
import android.os.Bundle;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class MoneyTransferNumberActivity extends BaseActivity {

  @Inject
  MoneyTransferActivityViewModel moneyTransferActivityViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_money_transfer_number);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.money_transfer));
    findViewById(R.id.verify)
        .setOnClickListener(view -> startActivity(new Intent(this, SelectRecepientActivity.class)));
  }
}