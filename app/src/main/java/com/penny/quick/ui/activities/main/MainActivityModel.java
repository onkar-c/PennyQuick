package com.penny.quick.ui.activities.main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModel {

  @Provides
  MainActivityViewModel provideMainActivityModel() {
    return new MainActivityViewModel();
  }

}
