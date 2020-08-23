package com.penny.core;

import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

  @POST("user/login")
  Call<JsonResponse> login(@Body LoginModel loginModel);

  @POST("user/login")
  Call<JsonResponse> requestOtp(@Query("nob_number") String mobileNumber);
}
