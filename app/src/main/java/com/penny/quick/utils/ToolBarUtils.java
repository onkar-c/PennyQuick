package com.penny.quick.utils;

import android.app.Activity;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.penny.quick.R;

public class ToolBarUtils {


  public static void setUpToolBar(AppCompatActivity context) {
    Toolbar toolbar = context.findViewById(R.id.toolBar);
    context.setSupportActionBar(toolbar);
    if (context.getSupportActionBar() != null) {
      context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      context.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    toolbar.setNavigationOnClickListener(view -> context.onBackPressed());
  }

  public static void setTitle(AppCompatActivity context, String title) {
    if (context.getSupportActionBar() != null) {
      context.getSupportActionBar().setTitle(title);
    }
  }
}
