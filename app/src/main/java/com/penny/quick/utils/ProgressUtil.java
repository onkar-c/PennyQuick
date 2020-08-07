package com.penny.quick.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressUtil {

  private ProgressDialog loading;

  public ProgressDialog getLoading() {
    return loading;
  }

  public void showLoading(Context context, String title, String message) {
    if (loading == null) {
      loading = new ProgressDialog(context);
      loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      loading.setTitle(title);
      loading.setMessage(message);
      loading.setIndeterminate(true);
      loading.setCancelable(false);
      if (!loading.isShowing()) {
        loading.show();
      }
    }
  }

  public void hideLoading() {
    if (loading != null) {
      loading.getContext();
      loading.hide();
      try {
        loading.dismiss();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    loading = null;
  }

  public void updateMessageOfLoader(String message) {
    if (loading != null && message != null) {
      try {
        loading.setMessage(message);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
