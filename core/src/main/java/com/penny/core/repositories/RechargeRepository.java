package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.models.DateFormatModel;
import com.penny.core.worker.MobileRechargeWorker;
import com.penny.core.worker.RecentRechargesWorker;
import com.penny.core.worker.RechargeStatusWorker;
import com.penny.database.AppDatabase;
import com.penny.database.ProjectConstants;
import com.penny.database.entities.RecentRecharge;
import java.util.List;

public class RechargeRepository extends BaseRepository {

  public LiveData<WorkInfo> getMobileRechargeWorkManager(String mobileNumber, float amount,
      String operator, String circle, String service) {
    Data.Builder data = getDataBuilderForApi(APITags.API_MOBILE_RECHARGE);
    data.putString(ProjectConstants.NUMBER, mobileNumber);
    data.putFloat(ProjectConstants.RECHARGE_AMOUNT, amount);
    data.putString(ProjectConstants.OPERATOR, operator);
    data.putString(ProjectConstants.CIRCLE, circle);
    data.putString(ProjectConstants.SERVICE, service);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(MobileRechargeWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_MOBILE_RECHARGE.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }


  public LiveData<WorkInfo> getRechargeStatusWorkManager(String transactionId, boolean isMoneyTransfer) {
    Data.Builder data = getDataBuilderForApi(APITags.API_RECHARGE_STATUS);
    data.putString(ProjectConstants.TRANSACTION_ID, transactionId);
    data.putBoolean(ProjectConstants.MONEY_TRANSFER, isMoneyTransfer);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(RechargeStatusWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_RECHARGE_STATUS.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getRecentRechargeWorkManager(List<DateFormatModel> dateFormatModels,
      List<String> extractedCategories, List<String> statusCategories) {
    Data.Builder data = getDataBuilderForApi(APITags.API_RECENT_RECHARGES);
    Gson gson = new Gson();
    data.putString(ProjectConstants.DATE_FILTER,
        gson.toJson(dateFormatModels, new TypeToken<List<DateFormatModel>>() {
        }.getType()));
    data.putString(ProjectConstants.CATEGORIES_FILTER,
        gson.toJson(extractedCategories, new TypeToken<List<String>>() {
        }.getType()));
    data.putString(ProjectConstants.STATUS_FILTER,
        gson.toJson(statusCategories, new TypeToken<List<String>>() {
        }.getType()));
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(RecentRechargesWorker.class)
        .setInputData(data.build())
//        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_RECENT_RECHARGES.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<List<RecentRecharge>> getRecentRecharges() {
    return AppDatabase.getInstance().getRecentRechargeEntityDao().getRecentRecharges();
  }
}
