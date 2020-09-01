package com.penny.quick.ui.activities.recent_recharge;

import dagger.Module;
import dagger.Provides;

@Module
public class RecentRechargeActivityModel {

  @Provides
  RecentRechargesActivityViewModel getRecentRechargeActivityModel() {
    return new RecentRechargesActivityViewModel();
  }
}
