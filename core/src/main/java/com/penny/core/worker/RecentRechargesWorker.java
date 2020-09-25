package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.DateFormatModel;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.RecentRechargesRequestModel;
import com.penny.database.AppDatabase;
import com.penny.database.ProjectConstants;
import java.util.List;

public class RecentRechargesWorker extends BaseWorker {

  public RecentRechargesWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    Gson gson = new Gson();
    List<DateFormatModel> dateFormatModels = gson
        .fromJson(getInputData().getString(ProjectConstants.DATE_FILTER),
            new TypeToken<List<DateFormatModel>>() {
            }.getType());
    List<String> categories = gson
        .fromJson(getInputData().getString(ProjectConstants.CATEGORIES_FILTER),
            new TypeToken<List<String>>() {
            }.getType());
    List<String> status = gson
        .fromJson(getInputData().getString(ProjectConstants.STATUS_FILTER),
            new TypeToken<List<String>>() {
            }.getType());
    RecentRechargesRequestModel recentRechargesRequestModel = new RecentRechargesRequestModel();
    recentRechargesRequestModel.setMonthFilter(dateFormatModels);
    recentRechargesRequestModel.setType(categories);
    recentRechargesRequestModel.setStatusType(status);
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .recentRecharges(recentRechargesRequestModel));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    AppDatabase.getInstance().getRecentRechargeEntityDao().deleteAll();
    AppDatabase.getInstance().getRecentRechargeEntityDao()
        .insert(jsonResponse.getRecentRecharges());
    return sendSuccess();
  }
}
