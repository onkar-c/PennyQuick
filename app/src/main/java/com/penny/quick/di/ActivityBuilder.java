package com.penny.quick.di;

import com.penny.quick.ui.activities.main.MainActivity;
import com.penny.quick.ui.activities.main.MainActivityModel;
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
}
