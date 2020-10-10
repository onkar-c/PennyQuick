package com.penny.quick.utils;

import static com.penny.quick.ui.activities.BaseActivity.manageBaseNetworkErr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.core.util.NetworkUtils;
import com.penny.quick.ui.activities.dash_board.DashBoardActivity;
import com.penny.quick.ui.activities.splash.SplashActivity;

public class NetworkConnectivityReceiver extends BroadcastReceiver {

  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction() != null && intent.getAction()
        .equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

      if (context instanceof DashBoardActivity) {
        NetworkConnectivityChangeListener listener = (DashBoardActivity) context;
        listener.networkStatusChange(NetworkUtils.isConnected(context));
      } else if (!(context instanceof SplashActivity)) {
        manageBaseNetworkErr((AppCompatActivity) context, NetworkUtils.isConnected(context));
      }
    }
  }

  public interface NetworkConnectivityChangeListener {

    void networkStatusChange(boolean status);
  }
}
