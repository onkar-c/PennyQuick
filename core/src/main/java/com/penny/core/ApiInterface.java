package com.penny.core;

import com.penny.core.models.ChangePasswordRequestModel;
import com.penny.core.models.ContactUsDisputeModel;
import com.penny.core.models.JsonResponse;
import com.penny.core.models.LoginRequestModel;
import com.penny.core.models.RequestRecipient;
import com.penny.core.models.TransferRequestModel;
import com.penny.core.models.rechargeRequestModel;
import com.penny.core.models.RecentRechargesRequestModel;
import com.penny.core.models.RequestMobileNumerModel;
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
  Call<JsonResponse> requestOtp(@Body RequestMobileNumerModel requestMobileNumerModel);

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
  Call<JsonResponse> rechargeStatus(@Query("txnId") String transactionId, @Query("transfer") Boolean transfer);

  @POST("recharge/transactions")
  Call<JsonResponse> recentRecharges(@Body RecentRechargesRequestModel recentRechargesRequestModel);

  @GET("recharge/provider")
  Call<JsonResponse> getProviders();

  @GET("disputeHistory")
  Call<JsonResponse> getDisputeHistory();

  @POST("forgot_password/contactUs")
  Call<JsonResponse> contactUs(@Body ContactUsDisputeModel contactUsDisputeModel);

  @POST("transfer/verifymobile")
  Call<JsonResponse> verifyMobileNumber(@Body RequestMobileNumerModel requestMobileNumerModel);

  @POST("transfer/enroll")
  Call<JsonResponse> enrollMobileNumber(@Body RequestMobileNumerModel requestMobileNumerModel);

  @POST("transfer/reSendOtp")
  Call<JsonResponse> moneyTransferOTP(@Body RequestMobileNumerModel requestMobileNumerModel);

  @POST("transfer/verifyotp")
  Call<JsonResponse> moneyTransferVerifyOtp(@Body VerifyOTPRequestModel verifyOTPRequestModel);

  @POST("transfer/listRecipient")
  Call<JsonResponse> moneyTransferGetRecipient(@Body RequestMobileNumerModel requestMobileNumerModel);

  @POST("transfer/addRecipient")
  Call<JsonResponse> moneyTransferAddRecipient(@Body RequestRecipient requestRecipient);

  @GET("transfer/getBankDetail")
  Call<JsonResponse> getBankDetails();

  @POST("transfer/trans")
  Call<JsonResponse> moneyTrans(@Body TransferRequestModel transferRequestModel);
}
