package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RequestOTPModel;
import com.penny.database.ProjectConstants;

public class RequestOTPWorker extends BaseWorker {

  public RequestOTPWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RequestOTPModel requestOTPModel = new RequestOTPModel();
    requestOTPModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .requestOtp(requestOTPModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
