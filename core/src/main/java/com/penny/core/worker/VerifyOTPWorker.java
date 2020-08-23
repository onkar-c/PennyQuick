package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.database.ProjectConstants;

public class VerifyOTPWorker extends BaseWorker {

  public VerifyOTPWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .verifyOtp(getInputData().getString(ProjectConstants.OTP)));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
