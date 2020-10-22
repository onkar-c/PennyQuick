package com.penny.quick.ui.activities.web_view;

import static com.penny.quick.ui.activities.BaseActivity.manageBaseNetworkErr;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.core.util.NetworkUtils;
import com.penny.database.ProjectConstants;
import com.penny.quick.R;
import com.penny.quick.utils.NetworkConnectivityReceiver;
import com.penny.quick.utils.ToolBarUtils;

public class WebViewActivity extends AppCompatActivity {

  private BroadcastReceiver mNetworkReceiver;

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web_view);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getIntent().getStringExtra(ProjectConstants.TITLE));
    registerNetworkReceiver();
    WebView webView = findViewById(R.id.web_view);

    WebSettings webSettings = webView.getSettings();
    webSettings.setJavaScriptEnabled(true);
    String url = getIntent().getStringExtra(ProjectConstants.URL);
    webView.loadUrl(url != null ? url :"https://www.google.com");
    webView.setWebViewClient(new WebViewClient());
  }

  protected void registerNetworkReceiver() {
    mNetworkReceiver = new NetworkConnectivityReceiver();
    IntentFilter filter = new IntentFilter();
    filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(mNetworkReceiver, filter);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    unregisterReceiver(mNetworkReceiver);
  }

  @Override
  protected void onResume() {
    super.onResume();
    if(!NetworkUtils.isConnected(this)) {
      manageBaseNetworkErr(this, NetworkUtils.isConnected(this));
    }
  }
}
