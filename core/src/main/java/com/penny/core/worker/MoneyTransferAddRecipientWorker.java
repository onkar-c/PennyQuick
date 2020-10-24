package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RequestRecipient;

public class MoneyTransferAddRecipientWorker extends BaseWorker {

  public MoneyTransferAddRecipientWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    RequestRecipient requestRecipient = new RequestRecipient();
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .moneyTransferAddRecipient(requestRecipient));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
