package com.penny.quick;

import android.app.Activity;
import android.content.Context;
import androidx.multidex.MultiDex;
import com.facebook.stetho.Stetho;
import com.penny.database.APP;
import com.penny.database.CoreSharedHelper;
import com.penny.quick.di.DaggerAppComponent;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import javax.inject.Inject;

public class AppDB extends APP implements HasActivityInjector {

  @Inject DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

  @Override
  public DispatchingAndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
    mContext = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this); // for Development to open database runtime
    CoreSharedHelper.init(this);
    DaggerAppComponent.builder().application(this).build().inject(this);
  }
}
