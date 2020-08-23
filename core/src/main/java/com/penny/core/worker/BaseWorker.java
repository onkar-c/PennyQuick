package com.penny.core.worker;

import static com.penny.core.APITags.DATA_CHANGE_MESSAGE;
import static com.penny.core.APITags.DATA_FAIL_RESPONSE;
import static com.penny.core.APITags.ERROR_WHILE_CONNECTING_TO_SERVER;
import static com.penny.core.APITags.TIME_OUT_ERROR;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.penny.core.APITags;
import com.penny.core.ApiClient;
import com.penny.core.ApiInterface;
import com.penny.core.models.ErrorJsonResponse;
import com.penny.core.models.JsonResponse;
import java.lang.annotation.Annotation;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

abstract class BaseWorker extends Worker {

  Data.Builder mData;
  private Converter<ResponseBody, ErrorJsonResponse> errorConverter;

  BaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
    mData = new Data.Builder();
    errorConverter =
        ApiClient.getClient().responseBodyConverter(ErrorJsonResponse.class, new Annotation[0]);
    mData.putInt(APITags.API_ID, getInputData().getInt(APITags.API_ID, 0));
  }

  void putErrorString(String errorMessage) {
    mData.putString(DATA_FAIL_RESPONSE, errorMessage);
  }

  Result execute(Call<JsonResponse> call) {
    try {
      Response<JsonResponse> response = call.execute();
      return onResponse(response);
    } catch (SocketTimeoutException e) {
      e.printStackTrace();
      putErrorString(TIME_OUT_ERROR);
    } catch (SocketException e) {
      e.printStackTrace();
      putErrorString(ERROR_WHILE_CONNECTING_TO_SERVER);
    } catch (Exception e) {
      e.printStackTrace();
      putErrorString(e.getMessage());
    }
    return sendFailure();
  }

  @NonNull
  @Override
  public Result doWork() {
    try {
      ApiClient.getClient().create(ApiInterface.class);
    } catch (Exception e) {
      putErrorString(e.getMessage());
      return Result.failure(mData.build());
    }
    return executeApi();
  }

  String getErrorString(Response<JsonResponse> response) {
    try {
      if (response.errorBody() != null) {
        ErrorJsonResponse errorJsonResponse = errorConverter.convert(response.errorBody());
        if (errorJsonResponse != null) {
          return errorJsonResponse.getMessage();
        }
      }
    } catch (Exception e) {
      return ERROR_WHILE_CONNECTING_TO_SERVER;
    }
    return ERROR_WHILE_CONNECTING_TO_SERVER;
  }

  public Result handleErrorResponse(Response<JsonResponse> response) {
    putErrorString(getErrorString(response));
    return sendFailure();
  }

  private Result onResponse(Response<JsonResponse> response) {
    if (response.isSuccessful() && response.body() != null) {
      if (response.body().isStatus().equals(APITags.SUCCESS)) {
        return onSuccessResponse(response.body());
      } else {
        putErrorString(response.body().getMessage());
        return sendFailure();
      }
    } else {
      return handleErrorResponse(response);
    }
  }

  Result sendSuccess() {
    return Result.success(mData.build());
  }

  Result sendFailure() {
    return Result.failure(mData.build());
  }

  protected abstract Result executeApi();

  protected abstract Result onSuccessResponse(JsonResponse jsonResponse);

  void updateLoadingMessage(String message) {
    mData.putString(DATA_CHANGE_MESSAGE, message);
    setProgressAsync(mData.build());
  }
}
