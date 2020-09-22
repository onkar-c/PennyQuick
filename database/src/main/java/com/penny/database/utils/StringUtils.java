package com.penny.database.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import com.penny.database.ProjectConstants;

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

  public static boolean isMobileNoValid(String mobileNoStr) {
    if (isEmptyString(mobileNoStr)) {
      return false;
    } if (mobileNoStr.length() != 10 ){
      return false;
    }  else {
      return mobileNoStr.matches(ProjectConstants.MOBILE_REGEX);
    }
  }

  public static boolean isPasswordValid(String pwdStr) {
    if (isEmptyString(pwdStr)) {
      return false;
    } /*else if (pwdStr.length() != 10 ){
      return  false;
    } */
    return true;
  }

  public static boolean isOtpValid(String otp1,String otp2,String otp3,String otp4) {
    return !isEmptyString(otp1) && !isEmptyString(otp2) && !isEmptyString(otp3) && !isEmptyString(
        otp4);
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
