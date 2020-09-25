package com.penny.quick.ui.activities;

import static com.penny.core.APITags.DEVICE_IS_OFFLINE;
import static com.penny.core.APITags.ERROR_WHILE_CONNECTING_TO_SERVER;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
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
import com.penny.quick.R;
import com.penny.quick.ui.activities.login.SignInActivity;
import com.penny.quick.utils.ProgressUtil;
import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;
import java.util.Set;

@SuppressLint("Registered")
public class BaseActivity extends DaggerAppCompatActivity {

  protected ProgressUtil mApiLoadingDialog;
  protected Toolbar toolbar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
  }

  public void setUpToolBar() {
    toolbar = findViewById(R.id.toolBar);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    toolbar.setNavigationOnClickListener(view -> onBackPressed());
  }

  public void setTitle(String title) {
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle(title);
    }
  }

//  protected void hideSoftKeyboard(View view) {
//    InputMethodManager imm =
//        (InputMethodManager) this.getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
//    imm.hideSoftInputFromWindow(view.getRootView().getWindowToken(), 0);
//  }

  protected void toast(String s) {
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

  protected void showMessageDialog(String title, String message) {
    android.app.AlertDialog.Builder messageDialog =
        new android.app.AlertDialog.Builder(this).setTitle(title).setMessage(message);
    messageDialog.setNeutralButton("OK", (dialog, which) -> {
    });
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
        .load(imgUrl)
        .placeholder(R.drawable.ic_user_profile_dummy)
        .into(imageView)
        .onLoadFailed(
            ContextCompat.getDrawable(BaseActivity.this, R.drawable.ic_user_profile_dummy));
  }

}
