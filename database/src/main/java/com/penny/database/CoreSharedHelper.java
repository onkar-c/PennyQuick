package com.penny.database;

import android.content.Context;
import android.content.SharedPreferences;

public class CoreSharedHelper {

  private static CoreSharedHelper instance;
  private final SharedPreferences sharedPreferences;

  private CoreSharedHelper(Context context) {
    sharedPreferences = context.getSharedPreferences(Constants.NAME, Context.MODE_PRIVATE);
  }

  public static void init(Context context) {
    instance = new CoreSharedHelper(context);
  }

  public static CoreSharedHelper getInstance() {
    if (instance == null) {
      throw new NullPointerException("CoreSharedHelper was not initialized!");
    }
    return instance;
  }

  private void savePref(String key, Object value) {
    SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
    if (value instanceof Boolean) {
      sharedPreferencesEditor.putBoolean(key, (boolean) value);
    } else if (value instanceof String) {
      sharedPreferencesEditor.putString(key, (String) value);
    } else if (value instanceof Integer) {
      sharedPreferencesEditor.putInt(key, (Integer) value);
    } else if (value instanceof Long) {
      sharedPreferencesEditor.putLong(key, (Long) value);
    }
    sharedPreferencesEditor.apply();
  }

  public boolean isFirstInstall() {
    return sharedPreferences.getBoolean(Constants.IS_FIRST_INSTALL, true);
  }

  public void setIsFirstInstall(boolean isFirstInstall) {
    savePref(Constants.IS_FIRST_INSTALL, isFirstInstall);
  }

  public boolean isLogin() {
    return sharedPreferences.getBoolean(Constants.IS_LOGIN, false);
  }

  public void setIsLogin(boolean isLogin) {
    savePref(Constants.IS_LOGIN, isLogin);
  }

  public boolean isRememberPassword() {
    return sharedPreferences.getBoolean(Constants.REMEMBER_PASSWORD, false);
  }

  public void setRememberPassword(boolean rememberPassword) {
    savePref(Constants.REMEMBER_PASSWORD, rememberPassword);
  }

  public void saveBaseURL(String url) {
    savePref(Constants.APP_BASE_URL, url);
  }

  public String getBaseURL() {
    return sharedPreferences.getString(Constants.APP_BASE_URL, "");
  }

  public void saveToken(String token) {
    savePref(Constants.JWT_TOKEN, token);
  }

  public String getToken() {
    return sharedPreferences.getString(Constants.JWT_TOKEN, null);
  }


  static class Constants {

    static final String JWT_TOKEN = "jwt token";
    static final String NAME = "pennyQuick";
    static final String APP_BASE_URL = "base url";
    static final String IS_FIRST_INSTALL = "is_first_install";
    static final String REMEMBER_PASSWORD = "remember_password";
    static final String IS_LOGIN = "is_login";
  }


}