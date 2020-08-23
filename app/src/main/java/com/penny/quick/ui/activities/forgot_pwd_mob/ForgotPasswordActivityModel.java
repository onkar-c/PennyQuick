package com.penny.quick.ui.activities.forgot_pwd_mob;

import dagger.Module;
import dagger.Provides;

@Module
public class ForgotPasswordActivityModel {

  @Provides
  ForgotPasswordViewModel provideSignInActivityModel() {
    return new ForgotPasswordViewModel();
  }
}
