package com.penny.quick.ui.activities.recent_recharge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.RechargeRepository;

public class RecentRechargesActivityViewModel extends ViewModel {

  RecentRechargesActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> getStatus(String transactionId) {
    return new RechargeRepository().getRechargeStatusWorkManager(transactionId);
  }
}
