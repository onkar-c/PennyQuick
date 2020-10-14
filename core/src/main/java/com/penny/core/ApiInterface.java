package com.penny.core;

import com.penny.core.models.ChangePasswordRequestModel;
import com.penny.core.models.ContactUsDisputeModel;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginRequestModel;
import com.penny.core.models.rechargeRequestModel;
import com.penny.core.models.RecentRechargesRequestModel;
import com.penny.core.models.RequestOTPModel;
import com.penny.core.models.VerifyOTPRequestModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

  @POST("recharge")
  Call<JsonResponse> recharge(@Body rechargeRequestModel rechargeRequestModel);

  @GET("recharge/status")
  Call<JsonResponse> rechargeStatus(@Query("txnId") String transactionId);

  @POST("recharge/transactions")
  Call<JsonResponse> recentRecharges(@Body RecentRechargesRequestModel recentRechargesRequestModel);

  @GET("recharge/provider")
  Call<JsonResponse> getProviders();

  @POST("forgot_password/contactUs")
  Call<JsonResponse> contactUs(@Body ContactUsDisputeModel contactUsDisputeModel);
}
