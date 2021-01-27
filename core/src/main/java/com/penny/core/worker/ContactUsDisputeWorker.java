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
    contactUsDisputeModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    contactUsDisputeModel.setMessage(getInputData().getString(ProjectConstants.MESSAGE));
    if (getInputData().getBoolean(ProjectConstants.IS_DISPUTE, false)) {
      contactUsDisputeModel.setTransactionId(getInputData().getString(ProjectConstants.SUBJECT));
      return execute(ApiClient.getClient().create(ApiInterface.class)
          .dispute(contactUsDisputeModel));
    } else {
      contactUsDisputeModel.setSubject(getInputData().getString(ProjectConstants.SUBJECT));
      return execute(ApiClient.getClient().create(ApiInterface.class)
          .contactUs(contactUsDisputeModel));
    }
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    return sendSuccess();
  }
}
