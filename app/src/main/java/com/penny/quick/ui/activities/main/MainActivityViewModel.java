package com.penny.quick.ui.activities.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.UserRepository;


class MainActivityViewModel extends ViewModel {

  MainActivityViewModel() {
    super();
  }

  LiveData<WorkInfo> performLogin(String pUserName, String pPassword) {
    return new UserRepository().getLoginWorkManager(pUserName, pPassword);
  }
}
