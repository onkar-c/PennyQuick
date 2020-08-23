package com.penny.quick.utils;

import android.annotation.SuppressLint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;

public class UiUtils {

  public static boolean showHidePassword(EditText etPassword, boolean isShow, MotionEvent event) {
    final int DRAWABLE_RIGHT = 2;
    if (event.getRawX() >= (etPassword.getRight() - etPassword
        .getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
      etPassword.setTransformationMethod(isShow ? HideReturnsTransformationMethod.getInstance()
          : PasswordTransformationMethod.getInstance());
      etPassword.setSelection(etPassword.getText().length());
      return true;
    }
    return false;
  }

  @SuppressLint("ClickableViewAccessibility")
  public static void setDisplayPasswordListener(EditText editText){
    editText.setOnTouchListener((v, event) -> {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        return showHidePassword(editText,false, event);
      } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
        return showHidePassword(editText,true, event);
      }
      return false;
    });
  }

}
