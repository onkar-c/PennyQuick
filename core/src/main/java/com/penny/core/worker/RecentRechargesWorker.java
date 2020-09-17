package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RecentRechargesRequestModel;
import com.penny.database.AppDatabase;

public class RecentRechargesWorker extends BaseWorker {

  public RecentRechargesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .recentRecharges(new RecentRechargesRequestModel()));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    AppDatabase.getInstance().getRecentRechargeEntityDao().insert(jsonResponse.getRecentRecharges());
    return sendSuccess();
  }
}
