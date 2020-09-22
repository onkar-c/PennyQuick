package com.penny.quick.ui.activities.mobile_recharge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.database.ProjectConstants;
import com.penny.database.utils.StringUtils;
import com.penny.database.entities.Operators;
import com.penny.quick.R;
import com.penny.quick.models.PlanModel;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivity;
import com.penny.quick.ui.activities.view_plans.ViewPlansActivity;
import com.penny.quick.ui.adapters.BottomSheetAdapter.BottomSheetListItemClickListener;
import com.penny.quick.ui.adapters.StatesBottomSheetAdapter.StateBottomSheetListItemClickListener;
import com.penny.quick.utils.OperatorBottomSheetDialog;
import com.penny.quick.utils.StateBottomSheetDialog;
import java.util.concurrent.Executors;
import javax.inject.Inject;

public class MobileRechargeActivity extends BaseActivity implements
    BottomSheetListItemClickListener, StateBottomSheetListItemClickListener {

  private static int VIEW_PLANS_REQ_CODE = 1;
  @Inject
  MobileRechargeActivityViewModel mobileRechargeActivityViewModel;
  private TextView tvOperator, tvState, tvError;
  private RadioButton rbPrepaid, rbPostpaid;
  private LinearLayout llPrepaid, llPostpaid, llPlanDetails;
  private OperatorBottomSheetDialog operatorSheetDialog;
  private StateBottomSheetDialog stateBottomSheetDialog;
  private EditText etMobileNo, etAmnt;
  private TextView tvTalktime, tvData, tvValidity, tvValidityDetails;
  private PlanModel selectedPlan;
  private Operators selectedOperator = null;
  private com.penny.database.entities.State selectedState = null;


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mobile_recharge);
    setUpToolBar();
    initUI();
    setListeners();
  }

  private void setListeners() {
    findViewById(R.id.bt_view_plans)
        .setOnClickListener(view -> startActivityForResult(new Intent(MobileRechargeActivity.this,
            ViewPlansActivity.class), VIEW_PLANS_REQ_CODE));

    findViewById(R.id.bt_recharge)
        .setOnClickListener(view -> {
          if (validateFields()) {
            recharge();
          }
        });

    rbPrepaid
        .setOnCheckedChangeListener((compoundButton, isChecked) -> setPrepaidSelected(isChecked));

    rbPostpaid
        .setOnCheckedChangeListener((compoundButton, isChecked) -> setPostpaidSelected(isChecked));

    llPrepaid.setOnClickListener(view -> {
      rbPrepaid.setChecked(true);
      setPrepaidSelected(rbPrepaid.isChecked());
    });
    llPostpaid.setOnClickListener(view -> {
      rbPostpaid.setChecked(true);
      setPostpaidSelected(rbPostpaid.isChecked());
    });

    tvOperator.setOnClickListener(
        view -> {
          String type = rbPostpaid.isChecked() ? ProjectConstants.POSTPAID_TYPE
              : ProjectConstants.PREPAID_TYPE;
          Executors.newSingleThreadExecutor()
              .execute(() -> {
                operatorSheetDialog = new OperatorBottomSheetDialog(
                    mobileRechargeActivityViewModel.getOperatorsByType(type));
                operatorSheetDialog
                    .show(getSupportFragmentManager(), ProjectConstants.OPERATOR);
              });
        });

    tvState.setOnClickListener(
        view -> Executors.newSingleThreadExecutor()
            .execute(() -> {
              stateBottomSheetDialog = new StateBottomSheetDialog(
                  mobileRechargeActivityViewModel.getStates());
              stateBottomSheetDialog
                  .show(getSupportFragmentManager(), ProjectConstants.STATE);
            }));
  }

  private void initUI() {
    setTitle("Mobile Recharge");
    rbPrepaid = findViewById(R.id.rb_prepaid);
    rbPostpaid = findViewById(R.id.rb_postpaid);
    llPrepaid = findViewById(R.id.ll_prepaid);
    llPrepaid.setSelected(false);
    llPostpaid = findViewById(R.id.ll_postpaid);
    llPostpaid.setSelected(false);
    llPlanDetails = findViewById(R.id.ll_plan_details);
    tvOperator = findViewById(R.id.et_operator);
    tvState = findViewById(R.id.et_state);
    tvError = findViewById(R.id.tv_error);
    etMobileNo = findViewById(R.id.et_mob_no);
    etAmnt = findViewById(R.id.et_amnt);

    tvTalktime = findViewById(R.id.tv_talkime);
    tvData = findViewById(R.id.tv_data);
    tvValidity = findViewById(R.id.tv_validity);
    tvValidityDetails = findViewById(R.id.tv_talktime_details);

//    etAmnt.addTextChangedListener(new TextWatcher() {
//      @Override
//      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//      }
//
//      @Override
//      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        if (!String.valueOf(selectedPlan.getAmount())
//            .equalsIgnoreCase(String.valueOf(charSequence))) {
//          llPlanDetails.setVisibility(View.GONE);
//        }
//      }
//
//      @Override
//      public void afterTextChanged(Editable editable) {
//      }
//    });
  }

  private boolean validateFields() {
    if (!rbPostpaid.isChecked() && !rbPrepaid.isChecked()) {
      showError(getString(R.string.recharge_type_error));
      return false;
    } else if (!StringUtils.isMobileNoValid(etMobileNo.getText().toString())) {
      showError(getString(R.string.mobile_number_incorrect));
      return false;
    } else if (StringUtils.isEmptyString(tvOperator.getText().toString())) {
      showError(getString(R.string.operator_error));
      return false;
    } else if (StringUtils.isEmptyString(tvState.getText().toString())) {
      showError(getString(R.string.state_error));
      return false;
    } else if (StringUtils.isEmptyString(etAmnt.getText().toString())) {
      showError(getString(R.string.amnt_error));
      return false;
    }
    tvError.setVisibility(View.GONE);
    return true;
  }

  private void showError(String error) {
    tvError.invalidate();
    tvError.setVisibility(View.VISIBLE);
    tvError.setText(error);
  }

  private void setPrepaidSelected(boolean isChecked) {
    if (isChecked) {
      rbPostpaid.setChecked(false);
      llPostpaid.setSelected(false);
      llPrepaid.setSelected(true);
    } else {
      llPrepaid.setSelected(false);
    }
  }

  private void setPostpaidSelected(boolean isChecked) {
    if (isChecked) {
      rbPrepaid.setChecked(false);
      llPrepaid.setSelected(false);
      llPostpaid.setSelected(true);
    } else {
      llPostpaid.setSelected(false);
    }
  }

  private void recharge() {
    mobileRechargeActivityViewModel
        .recharge(etMobileNo.getText().toString(), Float.parseFloat(etAmnt.getText().toString()),
            selectedOperator.getProvider(), selectedState.getStateCode(),
            rbPrepaid.isChecked() ? ProjectConstants.SERVICE_PREPAID_MOBILE
                : ProjectConstants.SERVICE_POSTPAID_MOBILE)
        .observe(this, this::mobileRechargeApiObserver);
  }

  private void mobileRechargeApiObserver(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        rechargeApiSuccess();
      }
    }
  }

  private void rechargeApiSuccess() {
    startActivity(new Intent(MobileRechargeActivity.this,
        TransactionStatusActivity.class));
  }

  @Override
  public void onBottomSheetListItemClick(Operators obj) {
    if (operatorSheetDialog != null && operatorSheetDialog.isVisible()) {
      operatorSheetDialog.dismiss();
    }
    selectedOperator = obj;
    tvOperator.setText(obj.getDisplay_name());
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == VIEW_PLANS_REQ_CODE) {
      if (resultCode == RESULT_OK) {
        if (data != null) {
          selectedPlan = data.getParcelableExtra(ProjectConstants.PLAN);
          if (selectedPlan != null) {
            llPlanDetails.setVisibility(View.VISIBLE);
            etAmnt.setText(String.valueOf(selectedPlan.getAmount()));
            tvTalktime.setText(String.format("$ %s", selectedPlan.getTalktime()));
            tvValidity.setText(selectedPlan.getValidity());
            tvData.setText(selectedPlan.getData());
            tvValidityDetails.setText(
                String.format("%s%s", getString(R.string.talktime_of), selectedPlan.getAmount()));
          }
        }
      }
    }
  }

  @Override
  public void onBottomSheetListItemClick(com.penny.database.entities.State obj) {
    if (stateBottomSheetDialog != null && stateBottomSheetDialog.isVisible()) {
      stateBottomSheetDialog.dismiss();
    }
    selectedState = obj;
    tvState.setText(obj.getDisplayName());
  }
}
