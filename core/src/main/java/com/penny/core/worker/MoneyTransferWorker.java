package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.google.gson.Gson;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RequestMobileNumerModel;
import com.penny.database.ProjectConstants;

public class MoneyTransferWorker extends BaseWorker {

  public MoneyTransferWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RequestMobileNumerModel requestMobileNumerModel = new RequestMobileNumerModel();
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .enrollMobileNumber(requestMobileNumerModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    mData.putString(ProjectConstants.TRANSACTION, new Gson().toJson(jsonResponse.getRecharge()));
    return sendSuccess();
  }
}
