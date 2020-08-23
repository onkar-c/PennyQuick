package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.LoginWorker;
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

}
