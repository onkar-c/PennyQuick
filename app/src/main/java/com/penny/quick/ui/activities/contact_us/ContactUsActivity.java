package com.penny.quick.ui.activities.contact_us;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.penny.quick.R;
import com.penny.quick.utils.ToolBarUtils;

public class ContactUsActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_us);
    ToolBarUtils.setUpToolBar(this);
    ToolBarUtils.setTitle(this, getString(R.string.contact_us));
  }
}