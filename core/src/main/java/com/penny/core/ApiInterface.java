package com.penny.core;

import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

  @POST("user/login")
  Call<JsonResponse> login(@Body LoginRequestModel loginRequestModel);

  @POST("user/requestOtp")
  Call<JsonResponse> requestOtp(@Query("nob_number") String mobileNumber);

  @POST("user/verifyOtp")
  Call<JsonResponse> verifyOtp(@Query("nob_number") String otp);

  @POST("user/changePassword")
  Call<JsonResponse> changePassword(@Query("nob_number") String password);

  @POST("user/userInfo")
  Call<JsonResponse> getUserInfo();
}
