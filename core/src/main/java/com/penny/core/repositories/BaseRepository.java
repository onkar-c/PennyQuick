package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import com.penny.core.APITags;
import com.penny.database.APP;

/**
 * Created by Onkar Chopade.
 */
public class BaseRepository {

  protected Data.Builder getDataBuilderForApi(int pApiId) {
    return new Data.Builder().putInt(APITags.API_ID, pApiId);
  }

  protected LiveData<WorkInfo> getOneTimeRequestLiveDate(OneTimeWorkRequest pRequest) {
    WorkManager mWorkManager = WorkManager.getInstance(APP.getContext());
    mWorkManager.enqueue(pRequest);
    return mWorkManager.getWorkInfoByIdLiveData(pRequest.getId());
  }
}
