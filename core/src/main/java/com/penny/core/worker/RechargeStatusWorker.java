package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RechargeStatusRequestModel;
import com.penny.database.ProjectConstants;

public class RechargeStatusWorker extends BaseWorker {

  public RechargeStatusWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RechargeStatusRequestModel rechargeStatusRequestModel = new RechargeStatusRequestModel();
    rechargeStatusRequestModel
        .setTransactionId(getInputData().getString(ProjectConstants.TRANSACTION_ID));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .rechargeStatus(rechargeStatusRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
