package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.LoginWorker;

public class UserRepository extends BaseRepository{

  public LiveData<WorkInfo> getLoginWorkManager(String pUserName, String pPassword) {
    Data.Builder data = getDataBuilderForApi(APITags.API_LOGIN);
//    data.putString(ProjectConstants.DATA_USER_NAME, pUserName);
//    data.putString(ProjectConstants.DATA_USER_PASSWORD, pPassword);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(LoginWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_LOGIN.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

}
