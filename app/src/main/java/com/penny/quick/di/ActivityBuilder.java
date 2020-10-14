package com.penny.quick.di;

import com.penny.quick.ui.activities.accept_details.AcceptRechargeDetails;
import com.penny.quick.ui.activities.change_password.ChangePasswordActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivity;
import com.penny.quick.ui.activities.contact_us_dispute.ContactUsDisputeActivityModel;
import com.penny.quick.ui.activities.dash_board.DashBoardActivity;
import com.penny.quick.ui.activities.dash_board.DashBoardActivityModel;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordActivityModel;
import com.penny.quick.ui.activities.forgot_pwd_mob.ForgotPasswordMobRegActivity;
import com.penny.quick.ui.activities.forgot_pwd_new_pwd.ForgotPasswordNewPwdActivity;
import com.penny.quick.ui.activities.forgot_pwd_otp.ForgotPasswordOtpActivity;
import com.penny.quick.ui.activities.login.SignInActivity;
import com.penny.quick.ui.activities.login.SignInActivityModel;
import com.penny.quick.ui.activities.main.MainActivity;
import com.penny.quick.ui.activities.main.MainActivityModel;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivity;
import com.penny.quick.ui.activities.mobile_recharge.MobileRechargeActivityModel;
import com.penny.quick.ui.activities.profile.ProfileActivity;
import com.penny.quick.ui.activities.profile.ProfileActivityModel;
import com.penny.quick.ui.activities.providersList.ProviderListActivityModel;
import com.penny.quick.ui.activities.providersList.ProvidersListActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeActivity;
import com.penny.quick.ui.activities.recent_recharge.RecentRechargeActivityModel;
import com.penny.quick.ui.activities.splash.SplashActivity;
import com.penny.quick.ui.activities.splash.SplashActivityModel;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivity;
import com.penny.quick.ui.activities.transaction_status.TransactionStatusActivityModel;
import com.penny.quick.ui.activities.view_plans.ViewPlanActivityModel;
import com.penny.quick.ui.activities.view_plans.ViewPlansActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = MainActivityModel.class)
  abstract MainActivity getMainActivity();

  @ContributesAndroidInjector(modules = SplashActivityModel.class)
  abstract SplashActivity getSplashActivity();

  @ContributesAndroidInjector(modules = SignInActivityModel.class)
  abstract SignInActivity getSignInActivity();

  @ContributesAndroidInjector(modules = ForgotPasswordActivityModel.class)
  abstract ForgotPasswordMobRegActivity getForgotPasswordMobRegActivity();

  @ContributesAndroidInjector(modules = ForgotPasswordActivityModel.class)
  abstract ForgotPasswordOtpActivity getForgotPasswordOtpActivity();

  @ContributesAndroidInjector(modules = ForgotPasswordActivityModel.class)
  abstract ForgotPasswordNewPwdActivity getForgotPasswordNewPwdActivity();

  @ContributesAndroidInjector(modules = ForgotPasswordActivityModel.class)
  abstract ChangePasswordActivity getChangePasswordActivity();

  @ContributesAndroidInjector(modules = TransactionStatusActivityModel.class)
  abstract TransactionStatusActivity getTransactionStatusActivity();

  @ContributesAndroidInjector(modules = RecentRechargeActivityModel.class)
  abstract RecentRechargeActivity getRecentRechargeActivity();

  @ContributesAndroidInjector(modules = DashBoardActivityModel.class)
  abstract DashBoardActivity getDashBoardActivity();

  @ContributesAndroidInjector(modules = ProviderListActivityModel.class)
  abstract ProvidersListActivity getProviderListActivity();

  @ContributesAndroidInjector(modules = MobileRechargeActivityModel.class)
  abstract MobileRechargeActivity getMobileRechargeActivity();

  @ContributesAndroidInjector(modules = MobileRechargeActivityModel.class)
  abstract AcceptRechargeDetails getAcceptRechargeDetails();

  @ContributesAndroidInjector(modules = ProfileActivityModel.class)
  abstract ProfileActivity getProfileActivity();

  @ContributesAndroidInjector(modules = ViewPlanActivityModel.class)
  abstract ViewPlansActivity getViewPlansActivity();

  @ContributesAndroidInjector(modules = ContactUsDisputeActivityModel.class)
  abstract ContactUsDisputeActivity getContactUsDisputeActivity();
}
