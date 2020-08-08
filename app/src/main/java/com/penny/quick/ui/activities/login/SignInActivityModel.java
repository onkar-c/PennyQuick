package com.penny.quick.ui.activities.login;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInActivityModel {

  @Provides
  SignActivityViewModel provideSignInActivityModel() {
    return new SignActivityViewModel();
  }
}
