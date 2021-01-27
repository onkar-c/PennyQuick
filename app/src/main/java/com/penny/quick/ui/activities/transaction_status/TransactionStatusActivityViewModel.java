package com.penny.quick.ui.activities.transaction_status;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.OperatorsRepository;
import com.penny.core.repositories.RechargeRepository;
import com.penny.database.entities.Operators;
import java.util.List;

public class TransactionStatusActivityViewModel extends ViewModel {

  TransactionStatusActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> getStatus(String transactionId, boolean isMoneyTransfer) {
    return new RechargeRepository().getRechargeStatusWorkManager(transactionId, isMoneyTransfer);
  }

  String getType(String type) {
    return new OperatorsRepository().getOperatorsNameByType(type);
  }

  List<Operators> getOperator(String provider) {
    return new OperatorsRepository().getOperatorsByProvider(provider);
  }
}
