package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RequestMobileNumerModel;
import com.penny.database.ProjectConstants;

public class MoneyTransferVerifyMobileNumberWorker extends BaseWorker {

  public MoneyTransferVerifyMobileNumberWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RequestMobileNumerModel requestMobileNumerModel = new RequestMobileNumerModel();
    requestMobileNumerModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .verifyMobileNumber(requestMobileNumerModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
