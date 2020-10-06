package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.ProvidersWorker;
import com.penny.database.AppDatabase;
import com.penny.database.entities.Operators;
import java.util.List;

public class OperatorsRepository extends BaseRepository {

  public void saveOperators(List<Operators> operatorsList) {
    AppDatabase.getInstance().getOperatorsDao().insert(operatorsList);
  }

  public List<Operators> getOperatorsByType(String type) {
    return AppDatabase.getInstance().getOperatorsDao().getOperatorsByType(type);
  }

  public String getOperatorsNameByType(String type) {
    return AppDatabase.getInstance().getOperatorsDao().getOperatorsNameByType(type);
  }

  public LiveData<WorkInfo> getProvidersWorkManager() {
    Data.Builder data = getDataBuilderForApi(APITags.API_PROVIDERS_LIST);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(ProvidersWorker.class)
        .setInputData(data.build())
        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_PROVIDERS_LIST.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }
}
