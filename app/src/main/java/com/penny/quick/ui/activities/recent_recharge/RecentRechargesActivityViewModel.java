package com.penny.quick.ui.activities.recent_recharge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.RechargeRepository;
import com.penny.database.entities.RecentRecharge;
import java.util.List;

public class RecentRechargesActivityViewModel extends ViewModel {

  RecentRechargesActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> getStatus(String transactionId) {
    return new RechargeRepository().getRechargeStatusWorkManager(transactionId);
  }

  LiveData<List<RecentRecharge>> getRecentRecharges() {
    return new RechargeRepository().getRecentRecharges();
  }

  LiveData<WorkInfo> getRecentRechargesFromServer() {
    return new RechargeRepository().getRecentRechargeWorkManager();
  }
}
