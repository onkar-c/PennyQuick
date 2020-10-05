package com.penny.quick.utils;

import static com.penny.quick.ui.activities.BaseActivity.manageBaseNetworkErr;
import static com.penny.quick.ui.activities.dash_board.DashBoardActivity.manageNetworkErr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.quick.ui.activities.dash_board.DashBoardActivity;
import com.penny.quick.ui.activities.splash.SplashActivity;

public class NetworkConnectivityReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction() != null && intent.getAction()
        .equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
      ConnectivityManager cm = (ConnectivityManager) context
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      assert cm != null;
      NetworkInfo netInfo = cm.getActiveNetworkInfo();
      if (context instanceof DashBoardActivity) {
        manageNetworkErr((AppCompatActivity) context, netInfo != null && netInfo.isConnected());
      } else if (!(context instanceof SplashActivity)) {
        manageBaseNetworkErr((AppCompatActivity) context, netInfo != null && netInfo.isConnected());
      }
    }
  }
}
