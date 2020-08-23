package com.penny.quick.ui.activities.dash_board;

import dagger.Module;
import dagger.Provides;

@Module
public class DashBoardActivityModel {

  @Provides
  DashBoardActivityViewModel provideDashBoardActivityModel() {
    return new DashBoardActivityViewModel();
  }
}
