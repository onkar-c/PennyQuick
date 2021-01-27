package com.penny.quick.utils;

import android.annotation.SuppressLint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;
import com.penny.core.models.DateFormatModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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

  public static List<DateFormatModel> generateDates() {
    List<DateFormatModel> dateFormatModels = new ArrayList<>();
    Calendar cal = Calendar.getInstance();
    for (int j = 0; j < 2; j++) {
      for (int i = j == 0 ? cal.get(Calendar.MONTH) : 11; i >= 0; i--) {
        cal.set(Calendar.MONTH, i);
        DateFormatModel dateFormatModel = new DateFormatModel();
        dateFormatModel.setId(UUID.randomUUID().toString());
        dateFormatModel
            .setMonthDisplay(
                cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        dateFormatModel.setMonth("" + (cal.get(Calendar.MONTH) + 1));
        dateFormatModel.setYear(String.valueOf(cal.get(Calendar.YEAR)));
        Log.d("date", dateFormatModel.getMonth() + " " + dateFormatModel.getYear() + " " + j);
        dateFormatModels.add(dateFormatModel);
      }
      cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - (j + 1));
    }
    return dateFormatModels;
  }
}
