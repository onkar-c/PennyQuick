package com.penny.quick.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.quick.R;

public class ForgotPasswordNewPwdActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forgot_pwd_new);

    findViewById(R.id.bt_change_pwd)
        .setOnClickListener(onDoneClick);
  }

  OnClickListener onDoneClick = view -> {
    startActivity(new Intent(ForgotPasswordNewPwdActivity.this, SignInActivity.class));
    finish();
  };
}
