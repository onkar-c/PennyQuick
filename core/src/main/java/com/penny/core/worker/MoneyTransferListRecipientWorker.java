package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RequestMobileNumerModel;
import com.penny.database.AppDatabase;
import com.penny.database.ProjectConstants;

public class MoneyTransferListRecipientWorker extends BaseWorker {

  public MoneyTransferListRecipientWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RequestMobileNumerModel requestMobileNumerModel = new RequestMobileNumerModel();
    requestMobileNumerModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .moneyTransferGetRecipient(requestMobileNumerModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    mData.putBoolean(ProjectConstants.RECIPIENT_AVAILABLE, false);
    if (jsonResponse.getRecipientList() != null && jsonResponse.getRecipientList().size() > 0) {
      AppDatabase.getInstance().getRecipientDao().deleteAll();
      AppDatabase.getInstance().getRecipientDao().insert(jsonResponse.getRecipientList());
      mData.putBoolean(ProjectConstants.RECIPIENT_AVAILABLE, true);
    }
    return sendSuccess();
  }
}
