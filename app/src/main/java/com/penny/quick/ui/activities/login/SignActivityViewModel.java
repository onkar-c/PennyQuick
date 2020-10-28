package com.penny.quick.ui.activities.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.MoneyTransferRepository;
import com.penny.core.repositories.OperatorsRepository;
import com.penny.core.repositories.UserRepository;

public class SignActivityViewModel extends ViewModel {

  SignActivityViewModel(){super();}

  LiveData<WorkInfo> performLogin(String pUserName, String pPassword) {
    return new UserRepository().getLoginWorkManager(pUserName, pPassword);
  }

  LiveData<WorkInfo> fetchProviders() {
    return new OperatorsRepository().getProvidersWorkManager();
  }

  LiveData<WorkInfo> fetchBankDetails() {
    return new MoneyTransferRepository().getBankDetailsWorkManager();
  }
}
