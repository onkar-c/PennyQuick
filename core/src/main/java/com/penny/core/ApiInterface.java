package com.penny.core;

import com.penny.core.models.JsonResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

  @POST("user/login")
  Call<JsonResponse> login(@Body RequestBody loginModel);
}
