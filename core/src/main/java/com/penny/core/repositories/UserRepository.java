package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.ChangePasswordWorker;
import com.penny.core.worker.ContactUsDisputeWorker;
import com.penny.core.worker.LoginWorker;
import com.penny.core.worker.RequestOTPWorker;
import com.penny.core.worker.UserInfoWorker;
import com.penny.core.worker.VerifyOTPWorker;
import com.penny.database.AppDatabase;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.User;

public class UserRepository extends BaseRepository {

  public LiveData<WorkInfo> getLoginWorkManager(String pUserName, String pPassword) {
    Data.Builder data = getDataBuilderForApi(APITags.API_LOGIN);
    data.putString(ProjectConstants.USER_NAME, pUserName);
    data.putString(ProjectConstants.PASSWORD, pPassword);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(LoginWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_LOGIN.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getRequestOTPWorkManager(String mobileNumber) {
    Data.Builder data = getDataBuilderForApi(APITags.API_REQUEST_OTP);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(RequestOTPWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_REQUEST_OTP.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getVerifyOTPWorkManager(String mobileNumber, String otp) {
    Data.Builder data = getDataBuilderForApi(APITags.API_VERIFY_OTP);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    data.putString(ProjectConstants.OTP, otp);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(VerifyOTPWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_VERIFY_OTP.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getChangePasswordWorkManager(String mobileNumber, String otp,
      String oldPassword, String password, boolean isForgotPassword) {
    Data.Builder data = getDataBuilderForApi(APITags.API_CHANGE_PASSWORD);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    data.putString(ProjectConstants.OTP, otp);
    data.putString(ProjectConstants.OLD_PASSWORD, oldPassword);
    data.putString(ProjectConstants.PASSWORD, password);
    data.putBoolean(ProjectConstants.IS_FORGOT_PASSWORD, isForgotPassword);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(ChangePasswordWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_CHANGE_PASSWORD.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getUserInfoWorkManager(boolean isBalanceRequest) {
    Data.Builder data = getDataBuilderForApi(
        isBalanceRequest ? APITags.API_USER_BALANCE : APITags.API_USER_INFO);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(UserInfoWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(isBalanceRequest ? APIEnums.API_USER_BALANCE.name() : APIEnums.API_USER_INFO.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getContactUsDisputeWorkManager(String name, String mobileNumber, String subject, String message) {
    Data.Builder data = getDataBuilderForApi(APITags.API_CONTACT_US);
    data.putString(ProjectConstants.USER_NAME, name);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    data.putString(ProjectConstants.SUBJECT, subject);
    data.putString(ProjectConstants.MESSAGE, message);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(ContactUsDisputeWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_CONTACT_US.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }


  public void upsertUser(User user) {
    if (AppDatabase.getInstance().getUserEntityDao().getUserByUserId(user.getUserId()).size()
        == 0) {
      AppDatabase.getInstance().getUserEntityDao().insert(user);
    } else {
      AppDatabase.getInstance().getUserEntityDao().update(user);
    }
  }

  public void deleteAllUsers() {
    AppDatabase.getInstance().getUserEntityDao().deleteAll();
  }

  public LiveData<User> getUser() {
    return AppDatabase.getInstance().getUserEntityDao().getUser();
  }

}
