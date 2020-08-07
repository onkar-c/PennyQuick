package com.penny.database;

import androidx.multidex.MultiDexApplication;

public class APP extends MultiDexApplication {

  protected static APP mContext;

  public static APP getContext() {
    return mContext;
  }
}
