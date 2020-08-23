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

  public LiveData<WorkInfo> verifyOTP(String otp) {
    return new UserRepository().getVerifyOTPWorkManager(otp);
  }

  public LiveData<WorkInfo> changePassword(String password) {
    return new UserRepository().getChangePasswordWorkManager(password);
  }
}
