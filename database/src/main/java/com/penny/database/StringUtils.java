package com.penny.database;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

public class StringUtils {

  public static boolean isEmptyString(Editable inputString) {
    if (inputString != null) {
      return inputString.toString().trim().length() == 0;
    }
    return true;
  }

  public static boolean isEmptyString(String inputString) {
    if (inputString != null) {
      return inputString.trim().length() == 0;
    }
    return true;
  }

//  public static boolean isEmailValid(String emailText) {
//    if (!isEmptyString(emailText)) {
//      return emailText.matches(RSPConstant.EMAIL_REX);
//    }
//    return false;
//  }

  public static SpannableString getTitleString(String title, String value) {
    SpannableString str = new SpannableString(title + value);
    str.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    str.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return str;
  }

}
