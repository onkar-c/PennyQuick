package com.penny.quick.ui.activities.mobile_recharge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.penny.quick.R;
import com.penny.quick.models.BottomSheetListObject;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivity;
import com.penny.quick.ui.adapters.BottomSheetAdapter;
import com.penny.quick.ui.adapters.BottomSheetAdapter.BottomSheetListItemClickListener;
import com.penny.quick.utils.OperatorBottomSheetDialog;
import com.penny.quick.utils.StateBottomSheetDialog;

public class MobileRechargeActivity extends BaseActivity implements
    BottomSheetListItemClickListener {

  private RadioButton rbPrepaid, rbPostpaid;
  private OperatorBottomSheetDialog operatorSheetDialog;
  private StateBottomSheetDialog stateBottomSheetDialog;
  TextView tvOperator,tvState;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mobile_recharge);
    setUpToolBar();
    setTitle("Mobile Recharge");

    rbPrepaid = findViewById(R.id.rb_prepaid);
    rbPostpaid = findViewById(R.id.rb_postpaid);
    findViewById(R.id.bt_recharge)
        .setOnClickListener(view -> startActivity(new Intent(MobileRechargeActivity.this,
            TransactionStatusActivity.class)));

    rbPrepaid.setOnCheckedChangeListener((compoundButton, isChecked) -> {
      if (isChecked) {
        rbPostpaid.setChecked(false);
      }
    });

    rbPostpaid.setOnCheckedChangeListener((compoundButton, isChecked) -> {
      if (isChecked) {
        rbPrepaid.setChecked(false);
      }
    });

    tvOperator = findViewById(R.id.et_operator);
    tvState = findViewById(R.id.et_state);

    tvOperator.setOnClickListener(
        view -> {
          operatorSheetDialog = new OperatorBottomSheetDialog();
          operatorSheetDialog.show(getSupportFragmentManager(), BottomSheetAdapter.OPERATOR_TYPE);
        });

    tvState.setOnClickListener(
        view -> {
          stateBottomSheetDialog = new StateBottomSheetDialog();
          stateBottomSheetDialog.show(getSupportFragmentManager(), BottomSheetAdapter.STATE_TYPE);
        });
  }

  @Override
  public void onBottomSheetListItemClick(BottomSheetListObject obj,String type) {
    if (operatorSheetDialog != null && operatorSheetDialog.isVisible()) {
      operatorSheetDialog.dismiss();
    }
    if (stateBottomSheetDialog != null && stateBottomSheetDialog.isVisible()) {
      stateBottomSheetDialog.dismiss();
    }
    if(type.equalsIgnoreCase(BottomSheetAdapter.OPERATOR_TYPE)){
      tvOperator.setText(obj.getName());
    }else if(type.equalsIgnoreCase(BottomSheetAdapter.STATE_TYPE)){
      tvState.setText(obj.getName());
    }
    Log.e("Operator Selected ", "Id" + obj.getId());
  }
}
