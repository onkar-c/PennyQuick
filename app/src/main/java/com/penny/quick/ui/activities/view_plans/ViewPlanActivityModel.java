package com.penny.quick.ui.activities.view_plans;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewPlanActivityModel {

  @Provides
  ViewPlanActivityViewModel provideViewPlansActivityModel() {
    return new ViewPlanActivityViewModel();
  }
}
