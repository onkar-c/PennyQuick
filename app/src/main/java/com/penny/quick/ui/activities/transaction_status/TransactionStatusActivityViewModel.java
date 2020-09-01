package com.penny.quick.ui.activities.transaction_status;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.RechargeRepository;

public class TransactionStatusActivityViewModel extends ViewModel {

  TransactionStatusActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> getStatus(String transactionId) {
    return new RechargeRepository().getRechargeStatusWorkManager(transactionId);
  }
}
