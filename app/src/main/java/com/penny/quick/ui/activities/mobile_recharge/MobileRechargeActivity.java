package com.penny.quick.ui.activities.mobile_recharge;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.adapters.BottomSheetAdapter.BottomSheetListItemClickListener;
import com.penny.quick.utils.BottomSheetUtils;

public class MobileRechargeActivity extends BaseActivity implements
    BottomSheetListItemClickListener {

  private RadioButton rbPrepaid, rbPostpaid;
  private BottomSheetBehavior bottomSheetBehavior;
  private EditText etOperator, etState;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mobile_recharge);
    setUpToolBar();
    setTitle("Mobile Recharge");

    rbPrepaid = findViewById(R.id.rb_prepaid);
    rbPostpaid = findViewById(R.id.rb_postpaid);

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
    BottomSheetUtils bottomSheetUtils = new BottomSheetUtils();
    bottomSheetBehavior = bottomSheetUtils.setUpBottomSheet(this);
    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

//    etOperator.setOnClickListener(
//        view -> {
//          bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//          bottomSheetUtils.setList(BottomSheetListObject.getObjectList(), MobileRechargeActivity.this);
//        });
  }

  @Override
  public void onBottomSheetListItemClick(int id) {
    Log.e("Operator Selected ", "Id" + id);
  }
}
