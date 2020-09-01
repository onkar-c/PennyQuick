package com.penny.core;

import com.penny.core.models.ChangePasswordRequestModel;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginRequestModel;
import com.penny.core.models.MobileRechargeRequestModel;
import com.penny.core.models.RecentRechargesRequestModel;
import com.penny.core.models.RechargeStatusRequestModel;
import com.penny.core.models.RequestOTPModel;
import com.penny.core.models.VerifyOTPRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

  @POST("user/login")
  Call<JsonResponse> login(@Body LoginRequestModel loginRequestModel);

  @POST("forgot_password/request_otp")
  Call<JsonResponse> requestOtp(@Body RequestOTPModel requestOTPModel);

  @POST("forgot_password/verify_otp")
  Call<JsonResponse> verifyOtp(@Body VerifyOTPRequestModel verifyOTPRequestModel);

  @POST("forgot_password/updatePassword")
  Call<JsonResponse> changePasswordWithOTP(
      @Body ChangePasswordRequestModel changePasswordRequestModel);

  @POST("user/updatepassword")
  Call<JsonResponse> changePassword(@Body ChangePasswordRequestModel changePasswordRequestModel);

  @GET("user")
  Call<JsonResponse> getUserInfo();

  @POST("mobileRecharge")
  Call<JsonResponse> mobileRecharge(@Body MobileRechargeRequestModel mobileRechargeRequestModel);

  @POST("rechargeStatus")
  Call<JsonResponse> rechargeStatus(@Body RechargeStatusRequestModel rechargeStatusRequestModel);

  @POST("rechargeStatus")
  Call<JsonResponse> recentRecharges(@Body RecentRechargesRequestModel recentRechargesRequestModel);
}
