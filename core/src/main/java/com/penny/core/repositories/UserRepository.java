package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.LoginWorker;
import com.penny.core.worker.RequestOTPWorker;
import com.penny.database.ProjectConstants;

public class UserRepository extends BaseRepository{

  public LiveData<WorkInfo> getLoginWorkManager(String pUserName, String pPassword) {
    Data.Builder data = getDataBuilderForApi(APITags.API_LOGIN);
    data.putString(ProjectConstants.USER_NAME, pUserName);
    data.putString(ProjectConstants.PASSWORD, pPassword);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(LoginWorker.class)
        .setInputData(data.build())
        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_LOGIN.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getRequestOTPWorkManager(String mobileNumber) {
    Data.Builder data = getDataBuilderForApi(APITags.API_REQUEST_OTP);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(RequestOTPWorker.class)
        .setInputData(data.build())
        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_REQUEST_OTP.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

}
