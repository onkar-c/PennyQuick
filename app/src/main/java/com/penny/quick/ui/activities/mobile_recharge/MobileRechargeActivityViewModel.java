package com.penny.quick.ui.activities.mobile_recharge;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.OperatorsRepository;
import com.penny.core.repositories.RechargeRepository;
import com.penny.core.repositories.StatesRepository;
import com.penny.database.entities.Operators;
import com.penny.database.entities.State;
import java.util.List;

public class MobileRechargeActivityViewModel extends ViewModel {

  MobileRechargeActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> recharge(String mobile, float amount, String operator, String circle,
      String service) {
    return new RechargeRepository()
        .getMobileRechargeWorkManager(mobile, amount, operator, circle, service);
  }

  List<Operators> getOperatorsByType(String type) {
    return new OperatorsRepository().getOperatorsByType(type);
  }

  List<State> getStates() {
    return new StatesRepository().getStates();
  }
}
