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

  @POST("user/login")
  Call<JsonResponse> requestOtp(@Query("nob_number") String mobileNumber);
}
