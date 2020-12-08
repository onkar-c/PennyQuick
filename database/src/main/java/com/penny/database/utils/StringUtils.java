package com.penny.database.utils;

import android.text.Editable;
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
      return true;
    }
    if (mobileNoStr.length() != 10) {
      return true;
    } else {
      return !mobileNoStr.matches(ProjectConstants.MOBILE_REGEX);
    }
  }

  public static boolean isPasswordValid(String pwdStr) {
    return isEmptyString(pwdStr);
  }

  public static boolean isOtpValid(String otp1, String otp2, String otp3, String otp4) {
    return !isEmptyString(otp1) && !isEmptyString(otp2) && !isEmptyString(otp3) && !isEmptyString(
        otp4);
  }

//  public static boolean isEmailValid(String emailText) {
//    if (!isEmptyString(emailText)) {
//      return emailText.matches(RSPConstant.EMAIL_REX);
//    }
//    return false;
//  }


}
