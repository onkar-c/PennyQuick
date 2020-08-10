package com.penny.quick.ui.activities.mobile_recharge;

import dagger.Module;
import dagger.Provides;

@Module
public class MobileRechargeActivityModel {

  @Provides
  MobileRechargeActivityViewModel MobileRechargeActivityModel() {
    return new MobileRechargeActivityViewModel();
  }
}
