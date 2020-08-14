package com.penny.quick.ui.activities.profile;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileActivityModel {

  @Provides
  ProfileActivityViewModel ProfileActivityModel() {
    return new ProfileActivityViewModel();
  }
}
