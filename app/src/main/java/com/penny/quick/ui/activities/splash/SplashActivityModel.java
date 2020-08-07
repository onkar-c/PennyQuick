package com.penny.quick.ui.activities.splash;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModel {

  @Provides
  SplashActivityViewModel provideMainActivityModel() {
    return new SplashActivityViewModel();
  }
}
