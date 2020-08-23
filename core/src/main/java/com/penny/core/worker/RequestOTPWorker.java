package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.database.CoreSharedHelper;
import com.penny.database.ProjectConstants;

public class RequestOTPWorker extends BaseWorker {

  public RequestOTPWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .requestOtp(getInputData().getString(ProjectConstants.MOBILE_NUMBER)));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    CoreSharedHelper.getInstance().saveToken(jsonResponse.getJwtToken());
    return sendSuccess();
  }
}
