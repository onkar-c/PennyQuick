package com.penny.quick.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
class AppModule {

  @Provides
  @Singleton
  Context provideContext(Application application) {
    return application;
  }
}
