package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.VerifyOTPRequestModel;
import com.penny.database.ProjectConstants;

public class MoneyTransferVerifyOTPWorker extends BaseWorker {

  public MoneyTransferVerifyOTPWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    VerifyOTPRequestModel verifyOTPRequestModel = new VerifyOTPRequestModel();
    verifyOTPRequestModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    verifyOTPRequestModel.setOtp(getInputData().getString(ProjectConstants.OTP));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .moneyTransferVerifyOtp(verifyOTPRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
