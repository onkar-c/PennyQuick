package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;

public class MoneyTransferTransWorker extends BaseWorker {

  public MoneyTransferTransWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .moneyTrans());
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
