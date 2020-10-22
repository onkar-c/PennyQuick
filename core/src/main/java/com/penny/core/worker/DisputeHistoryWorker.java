package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.database.AppDatabase;

public class DisputeHistoryWorker extends BaseWorker {

  public DisputeHistoryWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .getDisputeHistory());
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    AppDatabase.getInstance().getReportDao().deleteAll();
    AppDatabase.getInstance().getReportDao().insert(jsonResponse.getReports());
    return sendSuccess();
  }
}
