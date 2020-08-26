package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.ChangePasswordRequestModel;
import com.penny.core.models.JsonResponse;
import com.penny.database.ProjectConstants;

public class ChangePasswordWorker extends BaseWorker {

  public ChangePasswordWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    ChangePasswordRequestModel changePasswordRequestModel = new ChangePasswordRequestModel();
    changePasswordRequestModel.setPassword(getInputData().getString(ProjectConstants.PASSWORD));
    changePasswordRequestModel
        .setOld_password(getInputData().getString(ProjectConstants.OLD_PASSWORD));
    changePasswordRequestModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    changePasswordRequestModel.setOtp(getInputData().getString(ProjectConstants.OTP));
    if (getInputData().getBoolean(ProjectConstants.IS_FORGOT_PASSWORD, false)) {
      return execute(ApiClient.getClient().create(ApiInterface.class)
          .changePasswordWithOTP(changePasswordRequestModel));
    } else {
      return execute(ApiClient.getClient().create(ApiInterface.class)
          .changePassword(changePasswordRequestModel));
    }
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
