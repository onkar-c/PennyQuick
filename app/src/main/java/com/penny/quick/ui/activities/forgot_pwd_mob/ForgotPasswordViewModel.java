package com.penny.quick.ui.activities.forgot_pwd_mob;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.UserRepository;

public class ForgotPasswordViewModel extends ViewModel {

  ForgotPasswordViewModel(){super();}

  public LiveData<WorkInfo> requestOTP(String mobileNumber) {
    return new UserRepository().getRequestOTPWorkManager(mobileNumber);
  }
}
