package com.penny.quick.ui.activities.mobile_recharge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivity;
import com.penny.quick.ui.adapters.BottomSheetAdapter.BottomSheetListItemClickListener;
import com.penny.quick.utils.RechargeCommonBottomSheet;

public class MobileRechargeActivity extends BaseActivity implements
    BottomSheetListItemClickListener {

  private RadioButton rbPrepaid, rbPostpaid;
  private TextView etOperator, etState;
  RechargeCommonBottomSheet bottomSheetDialog;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mobile_recharge);
    setUpToolBar();
    setTitle("Mobile Recharge");

    rbPrepaid = findViewById(R.id.rb_prepaid);
    rbPostpaid = findViewById(R.id.rb_postpaid);
    ((AppCompatButton) findViewById(R.id.bt_recharge))
        .setOnClickListener(view -> startActivity(new Intent(MobileRechargeActivity.this,
            TransactionStatusActivity.class)));

    rbPrepaid.setOnCheckedChangeListener((compoundButton, isCheked) -> {
      if (isCheked) {
        rbPostpaid.setChecked(false);
      }
    });

    rbPostpaid.setOnCheckedChangeListener((compoundButton, isCheked) -> {
      if (isCheked) {
        rbPrepaid.setChecked(false);
      }
    });

    etOperator = findViewById(R.id.et_operator);
    etState = findViewById(R.id.et_state);

    etOperator.setOnClickListener(
        view -> {
          bottomSheetDialog= new RechargeCommonBottomSheet();
          bottomSheetDialog.show(getSupportFragmentManager(),"OperatorBottomSheet");
        });

    etState.setOnClickListener(
        view -> {
          bottomSheetDialog= new RechargeCommonBottomSheet();
          bottomSheetDialog.show(getSupportFragmentManager(),"StateBottomSheet");
        });
  }

  @Override
  public void onBottomSheetListItemClick(int id) {
    bottomSheetDialog.dismiss();
    Log.e("Operator Selected ", "Id" + id);
  }
}
