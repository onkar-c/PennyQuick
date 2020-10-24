package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.BankDetailsWorker;
import com.penny.core.worker.MoneyTransferAddRecipientWorker;
import com.penny.core.worker.MoneyTransferListRecipientWorker;
import com.penny.core.worker.MoneyTransferRequestOTPWorker;
import com.penny.core.worker.MoneyTransferVerifyMobileNumberWorker;
import com.penny.core.worker.MoneyTransferVerifyOTPWorker;
import com.penny.database.ProjectConstants;

public class MoneyTransferRepository extends BaseRepository {

  public LiveData<WorkInfo> getVerifyMobileNumberWorkManager(String mobileNumber) {
    Data.Builder data = getDataBuilderForApi(APITags.API_VERIFY_MOBILE_NUMBER);
    data.putString(ProjectConstants.NUMBER, mobileNumber);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(
        MoneyTransferVerifyMobileNumberWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_VERIFY_MOBILE_NUMBER.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getRequestOTPWorkManager(String mobileNumber) {
    Data.Builder data = getDataBuilderForApi(APITags.API_MONEY_TRANSFER_REQUEST_OTP);
    data.putString(ProjectConstants.NUMBER, mobileNumber);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(
        MoneyTransferRequestOTPWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_MONEY_TRANSFER_REQUEST_OTP.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getVerifyOTPWorkManager(String mobileNumber, String otp) {
    Data.Builder data = getDataBuilderForApi(APITags.API_MONEY_TRANSFER_VERIFY_OTP);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    data.putString(ProjectConstants.OTP, otp);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(MoneyTransferVerifyOTPWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_MONEY_TRANSFER_VERIFY_OTP.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getRecipientListWorkManager(String mobileNumber) {
    Data.Builder data = getDataBuilderForApi(APITags.API_MONEY_TRANSFER_RECIPIENT_LIST);
    data.putString(ProjectConstants.NUMBER, mobileNumber);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(
        MoneyTransferListRecipientWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_MONEY_TRANSFER_RECIPIENT_LIST.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> addRecipientWorkManager() {
    Data.Builder data = getDataBuilderForApi(APITags.API_MONEY_TRANSFER_ADD_RECIPIENT);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(
        MoneyTransferAddRecipientWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_MONEY_TRANSFER_ADD_RECIPIENT.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getBankDetailsWorkManager() {
    Data.Builder data = getDataBuilderForApi(APITags.API_BANK_DETAILS_LIST);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(BankDetailsWorker.class)
        .setInputData(data.build())
        .addTag(APIEnums.API_BANK_DETAILS_LIST.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }


}
