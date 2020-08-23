package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginRequestModel;
import com.penny.core.repositories.UserRepository;
import com.penny.database.CoreSharedHelper;
import com.penny.database.ProjectConstants;

public class LoginWorker extends BaseWorker {

  public LoginWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    CoreSharedHelper.getInstance().saveToken("");
    LoginRequestModel loginRequestModel = new LoginRequestModel();
    loginRequestModel.setUserName(getInputData().getString(ProjectConstants.USER_NAME));
    loginRequestModel.setPassword(getInputData().getString(ProjectConstants.PASSWORD));
    return execute(ApiClient.getClient().create(ApiInterface.class).login(loginRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    new UserRepository().deleteAllUsers();
    CoreSharedHelper.getInstance().saveToken(jsonResponse.getUser().getAuthKey());
    new UserRepository().upsertUser(jsonResponse.getUser());
    return sendSuccess();
  }
}
