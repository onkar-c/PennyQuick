package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.rechargeRequestModel;
import com.penny.database.ProjectConstants;

public class MobileRechargeWorker extends BaseWorker {

  public MobileRechargeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    rechargeRequestModel rechargeRequestModel = new rechargeRequestModel();
    rechargeRequestModel
        .setNumber(getInputData().getString(ProjectConstants.NUMBER));
    rechargeRequestModel
        .setAmount(getInputData().getFloat(ProjectConstants.RECHARGE_AMOUNT, 0));
    rechargeRequestModel.setCircle(getInputData().getString(ProjectConstants.CIRCLE));
    rechargeRequestModel.setOperator(getInputData().getString(ProjectConstants.OPERATOR));
    rechargeRequestModel.setService(getInputData().getString(ProjectConstants.SERVICE));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .recharge(rechargeRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
