package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.database.AppDatabase;

public class BankDetailsWorker extends BaseWorker {

  public BankDetailsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .getBankDetails());
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    if (jsonResponse.getBankDetailsList() != null && jsonResponse.getBankDetailsList().size() > 0) {
      AppDatabase.getInstance().getBankDetailsDao().deleteAll();
      AppDatabase.getInstance().getBankDetailsDao().insert(jsonResponse.getBankDetailsList());
    }
    return sendSuccess();
  }
}
