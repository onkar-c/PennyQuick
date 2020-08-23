package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.repositories.UserRepository;

public class UserInfoWorker extends BaseWorker {

  public UserInfoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    return execute(ApiClient.getClient().create(ApiInterface.class).getUserInfo());
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    new UserRepository().upsertUser(jsonResponse.getUser());
    return sendSuccess();
  }
}
