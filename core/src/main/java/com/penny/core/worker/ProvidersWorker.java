package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.database.AppDatabase;

public class ProvidersWorker extends BaseWorker {

  public ProvidersWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .getProviders());
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    if (jsonResponse.getProviderList() != null && jsonResponse.getProviderList().size() > 0) {
      AppDatabase.getInstance().getOperatorsDao().insert(jsonResponse.getProviderList());
    }
    return sendSuccess();
  }
}
