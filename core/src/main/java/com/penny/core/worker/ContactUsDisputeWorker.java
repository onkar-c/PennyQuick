package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.ContactUsDisputeModel;
import com.penny.core.models.JsonResponse;
import com.penny.database.ProjectConstants;

public class ContactUsDisputeWorker extends BaseWorker {

  public ContactUsDisputeWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    ContactUsDisputeModel contactUsDisputeModel = new ContactUsDisputeModel();
    contactUsDisputeModel.setName(getInputData().getString(ProjectConstants.USER_NAME));
    contactUsDisputeModel.setMobileNumber(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    contactUsDisputeModel.setSubject(getInputData().getString(ProjectConstants.SUBJECT));
    contactUsDisputeModel.setMessage(getInputData().getString(ProjectConstants.MESSAGE));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .contactUs(contactUsDisputeModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
