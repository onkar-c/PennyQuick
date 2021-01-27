package com.penny.core.worker;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.MonthRequest;
import com.penny.core.models.ReportsRequest;
import com.penny.database.AppDatabase;
import com.penny.database.ProjectConstants;
import java.util.List;

public class ReportsWorker extends BaseWorker {

  public ReportsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }

  @Override
  protected Result executeApi() {
    Gson gson = new Gson();
    List<MonthRequest> months = gson
        .fromJson(getInputData().getString(ProjectConstants.DATE_FILTER),
            new TypeToken<List<MonthRequest>>() {
            }.getType());
    List<String> categories = gson
        .fromJson(getInputData().getString(ProjectConstants.CATEGORIES_FILTER),
            new TypeToken<List<String>>() {
            }.getType());
    ReportsRequest reportsRequest = new ReportsRequest();
    reportsRequest.setMonthFilter(months);
    reportsRequest.setType(categories);
    return execute(ApiClient.getClient().create(ApiInterface.class)
        .getReports(reportsRequest));
  }

  @Override
  protected Result onSuccessResponse(JsonResponse jsonResponse) {
    AppDatabase.getInstance().getReportDao().deleteAll();
    AppDatabase.getInstance().getReportDao()
        .insert(jsonResponse.getReports());
    return sendSuccess();
  }
}
