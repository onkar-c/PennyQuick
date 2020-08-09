package com.penny.quick.ui.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.penny.quick.R;
import com.penny.quick.ui.activities.dash_board.DashBoardActivity;

public class SuccessLoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_success_login);
    ((Button) findViewById(R.id.bt_sign_in_continue))
        .setOnClickListener(view -> startActivity(new Intent(SuccessLoginActivity.this,
            DashBoardActivity.class)));
  }
}
