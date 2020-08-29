package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.MobileRechargeRequestModel;
import com.penny.database.ProjectConstants;

public class MobileRechargeWorker extends BaseWorker {

  public MobileRechargeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    MobileRechargeRequestModel mobileRechargeRequestModel = new MobileRechargeRequestModel();
    mobileRechargeRequestModel
        .setMobileNumber(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    mobileRechargeRequestModel
        .setAmount(getInputData().getFloat(ProjectConstants.RECHARGE_AMOUNT, 0));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .mobileRecharge(mobileRechargeRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
