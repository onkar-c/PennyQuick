package com.penny.quick.ui.activities;

import static com.penny.core.APITags.DEVICE_IS_OFFLINE;
import static com.penny.core.APITags.ERROR_WHILE_CONNECTING_TO_SERVER;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.work.Data;
import androidx.work.WorkInfo;
import androidx.work.WorkInfo.State;
import androidx.work.WorkManager;
import com.bumptech.glide.Glide;
import com.penny.core.APITags;
import com.penny.core.APITags.APIEnums;
import com.penny.core.util.NetworkUtils;
import com.penny.database.CoreSharedHelper;
import com.penny.quick.AppDB;
import com.penny.quick.R;
import com.penny.quick.ui.activities.login.SignInActivity;
import com.penny.quick.utils.NetworkConnectivityReceiver;
import com.penny.quick.utils.ProgressUtil;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.Set;

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

  protected ProgressUtil mApiLoadingDialog;
  protected Toolbar toolbar;
  private BroadcastReceiver mNetworkReceiver;

  public static void showWarningText(TextView networkErrTV, boolean isNetworkAvailable) {
    if (isNetworkAvailable) {
      networkErrTV.setText(AppDB.getContext().getString(R.string.network_availbale));
      networkErrTV.setBackgroundColor(AppDB.getContext().getResources().getColor(R.color.transaction_success));
      animateOut(networkErrTV);
    } else {
      animateIn(networkErrTV);
      networkErrTV.setText(AppDB.getContext().getString(R.string.network_not_availbale));
      networkErrTV.setBackgroundColor(AppDB.getContext().getResources().getColor(R.color.transaction_failed));
    }
  }

  static void animateIn(TextView warningTV) {
    warningTV.animate()
        .alpha(1.0f)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            warningTV.setVisibility(View.VISIBLE);
            warningTV.setAlpha(0.0f);
          }

          @Override
          public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            warningTV.animate().setListener(null);
          }
        });
  }

  static void animateOut(TextView warningTV) {
    warningTV.animate()
        .translationY(0).alpha(0.0f)
        .setDuration(1000)
        .setListener(new AnimatorListenerAdapter() {
          @Override
          public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            warningTV.setVisibility(View.GONE);
            warningTV.animate().setListener(null);
          }
        });
  }

  public static void manageBaseNetworkErr(AppCompatActivity context, boolean isNetworkAvailable) {
    TextView networkWarningTV = context.findViewById(R.id.tv_network_warning);
    showWarningText(networkWarningTV, isNetworkAvailable);
  }

//  protected void hideSoftKeyboard(View view) {
//    InputMethodManager imm =
//        (InputMethodManager) this.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
//    imm.hideSoftInputFromWindow(view.getRootView().getWindowToken(), 0);
//  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
  }

  protected void registerNetworkReceiver() {
    mNetworkReceiver = new NetworkConnectivityReceiver();
    IntentFilter filter = new IntentFilter();
    filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    registerReceiver(mNetworkReceiver, filter);
  }

  public void setUpToolBar() {
    toolbar = findViewById(R.id.toolBar);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    toolbar.setNavigationOnClickListener(view -> onBackPressed());
    registerNetworkReceiver();
  }

  public void setTitle(String title) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(title);
    }
  }

  public void toast(String s) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
  }

  private String getTagFromWorkInfo(WorkInfo workInfo) {
    Set<String> tags = workInfo.getTags();
    if (!tags.isEmpty()) {
      for (String tag : tags) {
        if (!tag.contains("com.penny.core")) {
          return tag;
        }
      }
    }
    return APIEnums.API_DEFAULT.name();
  }

  protected void showApiLoadingDialog(String title, String message) {
    if (mApiLoadingDialog == null) {
      mApiLoadingDialog = new ProgressUtil();
    }
    if (mApiLoadingDialog.getLoading() == null) {
      mApiLoadingDialog.showLoading(this, title, message);
    } else if (!mApiLoadingDialog.getLoading().isShowing()) {
      mApiLoadingDialog.showLoading(this, title, message);
    }
  }

  protected void changeMessage(String message) {
    if (mApiLoadingDialog != null) {
      mApiLoadingDialog.updateMessageOfLoader(message);
    }
  }

  public void apiResponseHandler(WorkInfo workInfo) {

    WorkInfo.State state = workInfo.getState();
    if (state == State.ENQUEUED) {
      String apiTag = getTagFromWorkInfo(workInfo);
      showApiLoadingDialog(
          APITags.APIEnums.valueOf(apiTag).getTitle(),
          APITags.APIEnums.valueOf(apiTag).getMessage());
    } else if (state == State.SUCCEEDED) {
      hideApiLoadingDialog();
    } else if (state == State.FAILED) {
      WorkManager.getInstance(this).cancelWorkById(workInfo.getId());
      String error = workInfo.getOutputData().getString(APITags.DATA_FAIL_RESPONSE);
      hideApiLoadingDialog();
      if (error != null) {
        error = handleOfflineException(error);
        if (error.equals(APITags.INVALID_AUTH)) {
          toast(error);
          performLogout();
        } else {
          int apiId = workInfo.getOutputData().getInt(APITags.API_ID, 0);
          if (apiId == 0) {
            toast(error);
          } else {
            responseErrorHandling(apiId, error);
          }
        }
      }
    } else if (state == State.RUNNING) {
      Data data = workInfo.getProgress();
      if (data.getString(APITags.DATA_CHANGE_MESSAGE) != null) {
        changeMessage(data.getString(APITags.DATA_CHANGE_MESSAGE));
      }
    }
  }

  private String handleOfflineException(String error) {
    return error.equals(ERROR_WHILE_CONNECTING_TO_SERVER) && !NetworkUtils.isConnected(this)
        ? DEVICE_IS_OFFLINE
        : error;
  }

  public void hideApiLoadingDialog() {
    if (mApiLoadingDialog != null) {
      mApiLoadingDialog.hideLoading();
    }
  }

  protected void showMessageDialog(String title, String message, OnClickListener okOnClickListener) {
    android.app.AlertDialog.Builder messageDialog =
        new android.app.AlertDialog.Builder(this).setTitle(title).setMessage(message);
    messageDialog.setPositiveButton("OK",okOnClickListener);
    messageDialog.setCancelable(false);
    messageDialog.show();
  }

  public void responseErrorHandling(int pApiId, String error) {
    toast(error);
  }

  protected void performLogout() {
    CoreSharedHelper.getInstance().saveToken("");
    CoreSharedHelper.getInstance().setIsLogin(false);
    CoreSharedHelper.getInstance().setRememberPassword(false);
    startActivity(new Intent(this, SignInActivity.class));
    finish();
  }

  public void showProfileImage(String imgUrl, ImageView imageView) {
    Glide.with(this)
        .load(imgUrl != null ? imgUrl : "")
        .placeholder(R.drawable.ic_person_24)
        .into(imageView)
        .onLoadFailed(
            ContextCompat.getDrawable(BaseActivity.this, R.drawable.ic_person_24));
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    try {
      if (mNetworkReceiver != null) {
        unregisterReceiver(mNetworkReceiver);
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
