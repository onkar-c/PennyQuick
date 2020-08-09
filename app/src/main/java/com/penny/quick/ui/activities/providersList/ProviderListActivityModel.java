package com.penny.quick.ui.activities.providersList;

import dagger.Module;
import dagger.Provides;

@Module
public class ProviderListActivityModel {

  @Provides
  ProviderListViewModel provideMainActivityModel() {
    return new ProviderListViewModel();
  }
}
