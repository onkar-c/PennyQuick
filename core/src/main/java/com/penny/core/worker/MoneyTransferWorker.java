package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.google.gson.Gson;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.TransferRequestModel;
import com.penny.database.ProjectConstants;

public class MoneyTransferWorker extends BaseWorker {

  public MoneyTransferWorker(@NonNull Context context,
      @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    TransferRequestModel transferRequestModel = new TransferRequestModel();
    transferRequestModel.setMobile(getInputData().getString(ProjectConstants.MOBILE_NUMBER));
    transferRequestModel.setName(getInputData().getString(ProjectConstants.USER_NAME));
    transferRequestModel.setRecipientId(getInputData().getString(ProjectConstants.RECIPIENT_ID));
    transferRequestModel.setIfsc(getInputData().getString(ProjectConstants.IFSC));
    transferRequestModel.setAccount(getInputData().getString(ProjectConstants.ACCOUNT));
    transferRequestModel.setCustId(getInputData().getString(ProjectConstants.CUSTOMER_ID));
    transferRequestModel.setTxtAmount(getInputData().getString(ProjectConstants.AMOUNT));
    transferRequestModel.setMerchantDocumentIdType(getInputData().getString(ProjectConstants.DOC_ID_TYPE));
    transferRequestModel.setMerchantDocumentId(getInputData().getString(ProjectConstants.DOC_ID));
    transferRequestModel.setPincode(getInputData().getString(ProjectConstants.PIN_CODE));
    transferRequestModel.setTransactionType(getInputData().getString(ProjectConstants.TRANSACTION));
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .moneyTrans(transferRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    mData.putString(ProjectConstants.TRANSACTION, new Gson().toJson(jsonResponse.getTransfer()));
    return sendSuccess();
  }
}
