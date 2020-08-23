package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginModel;
import com.penny.database.CoreSharedHelper;
import com.penny.database.ProjectConstants;

public class LoginWorker extends BaseWorker {

  public LoginWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    CoreSharedHelper.getInstance().saveToken("");
    LoginModel loginModel = new LoginModel();
    loginModel.setUserName(getInputData().getString(ProjectConstants.USER_NAME));
    loginModel.setPassword(getInputData().getString(ProjectConstants.PASSWORD));
    return execute(ApiClient.getClient().create(ApiInterface.class).login(loginModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    CoreSharedHelper.getInstance().saveToken(jsonResponse.getJwtToken());
    return sendSuccess();
  }
}
