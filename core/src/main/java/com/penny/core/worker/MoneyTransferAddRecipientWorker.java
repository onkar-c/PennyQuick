package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RequestRecipient;
import com.penny.database.ProjectConstants;

public class MoneyTransferAddRecipientWorker extends BaseWorker {

  public MoneyTransferAddRecipientWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RequestRecipient requestRecipient = new RequestRecipient();
    requestRecipient.setBankCode(getInputData().getString(ProjectConstants.BANK_CODE));
    requestRecipient.setAccount(getInputData().getString(ProjectConstants.ACCOUNT));
    requestRecipient.setIfscRequired(true);
    requestRecipient.setCustId(getInputData().getString(ProjectConstants.CUSTOMER_ID));
    requestRecipient.setIfsc(getInputData().getString(ProjectConstants.IFSC));
    requestRecipient.setRecipientName(getInputData().getString(ProjectConstants.USER_NAME));
    requestRecipient.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .moneyTransferAddRecipient(requestRecipient));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
