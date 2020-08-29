package com.penny.quick.ui.activities.mobile_recharge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.RechargeRepository;

public class MobileRechargeActivityViewModel extends ViewModel {

  MobileRechargeActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> recharge(String mobile, float amount) {
    return new RechargeRepository().getMobileRechargeWorkManager(mobile, amount);
  }
}
