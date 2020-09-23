package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.google.gson.Gson;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.database.ProjectConstants;

public class RechargeStatusWorker extends BaseWorker {

  public RechargeStatusWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .rechargeStatus(getInputData().getString(ProjectConstants.TRANSACTION_ID)));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    mData.putString(ProjectConstants.TRANSACTION, new Gson().toJson(jsonResponse.getRecharge()));
    return sendSuccess();
  }
}
