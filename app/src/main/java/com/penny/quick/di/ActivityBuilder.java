package com.penny.quick.di;

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
import com.penny.quick.ui.activities.splash.SplashActivity;
import com.penny.quick.ui.activities.splash.SplashActivityModel;
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

  @ContributesAndroidInjector(modules = ProviderListActivityModel.class)
  abstract ProvidersListActivity getProviderListActivity();

  @ContributesAndroidInjector(modules = MobileRechargeActivityModel.class)
  abstract MobileRechargeActivity getMobileRechargeActivity();

  @ContributesAndroidInjector(modules = ProfileActivityModel.class)
  abstract ProfileActivity getProfileActivity();
}
