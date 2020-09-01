package com.penny.core.repositories;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.worker.MobileRechargeWorker;
import com.penny.core.worker.RecentRechargesWorker;
import com.penny.core.worker.RechargeStatusWorker;
import com.penny.database.ProjectConstants;

public class RechargeRepository extends BaseRepository {

  public LiveData<WorkInfo> getMobileRechargeWorkManager(String mobileNumber, float amount) {
    Data.Builder data = getDataBuilderForApi(APITags.API_MOBILE_RECHARGE);
    data.putString(ProjectConstants.MOBILE_NUMBER, mobileNumber);
    data.putFloat(ProjectConstants.RECHARGE_AMOUNT, amount);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(MobileRechargeWorker.class)
        .setInputData(data.build())
        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_MOBILE_RECHARGE.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }


  public LiveData<WorkInfo> getRechargeStatusWorkManager(String transactionId) {
    Data.Builder data = getDataBuilderForApi(APITags.API_RECHARGE_STATUS);
    data.putString(ProjectConstants.TRANSACTION_ID, transactionId);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(RechargeStatusWorker.class)
        .setInputData(data.build())
        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_RECHARGE_STATUS.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }

  public LiveData<WorkInfo> getRecentRechargeWorkManager() {
    Data.Builder data = getDataBuilderForApi(APITags.API_RECENT_RECHARGES);
    OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(RecentRechargesWorker.class)
        .setInputData(data.build())
        .setConstraints(getNetworkConstraint())
        .addTag(APIEnums.API_RECENT_RECHARGES.name())
        .build();
    return getOneTimeRequestLiveDate(mRequest);
  }
}
