package com.penny.quick.ui.activities.contact_us_dispute;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import com.penny.core.APITags;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.ui.activities.BaseActivity;
import com.penny.quick.utils.ToolBarUtils;
import javax.inject.Inject;

public class ContactUsDisputeActivity extends BaseActivity {

  @Inject
  ContactUsDisputeViewModel contactUsDisputeViewModel;
  private EditText customerName, mobileNumber, subject, message;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_us);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(
        getIntent().getBooleanExtra(ProjectConstants.IS_DISPUTE, false) ? R.string.dispute_form
            : R.string.contact_us));
    initUi();
    registerNetworkReceiver();
  }

  private void initUi() {
    customerName = findViewById(R.id.et_customer_name);
    mobileNumber = findViewById(R.id.et_mobile_number);
    subject = findViewById(R.id.et_subject);
    message = findViewById(R.id.et_re_enter_message);
    if (getIntent().getBooleanExtra(ProjectConstants.IS_DISPUTE, false)) {
      ((TextView) findViewById(R.id.subject)).setText(R.string.transaction_id);
      subject.setHint(getString(R.string.enter_transaction_id));
    }
    findViewById(R.id.save).setOnClickListener(view -> saveData());
  }

  private void saveData() {
    if (NetworkUtils.isConnected(this)) {
      contactUsDisputeViewModel
          .sendData(customerName.getText().toString(), mobileNumber.getText().toString(),
              subject.getText().toString(), message.getText().toString(),
              getIntent().getBooleanExtra(ProjectConstants.IS_DISPUTE, false)).observe(this,
          this::observeSendDataApi);
    } else {
      toast(APITags.DEVICE_IS_OFFLINE);
    }

  }

  private void observeSendDataApi(WorkInfo workInfo) {
    if (workInfo != null) {
      State state = workInfo.getState();
      apiResponseHandler(workInfo);
      if (state == State.SUCCEEDED) {
        showMessageDialog("", getString(R.string.dispute_success), (dialog, which) -> {
          dialog.dismiss();
          ContactUsDisputeActivity.this.finish();
        });
      }
    }

  }


  @Override
  protected void onResume() {
    super.onResume();
    if (!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
  }
}