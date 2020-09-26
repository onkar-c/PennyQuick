package com.penny.database.utils;

import com.penny.database.ProjectConstants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

  public static Date convertToDate(String date, String dateFormat) {
    try {
      SimpleDateFormat lSimpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
      return lSimpleDateFormat.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String getDateString(String format, Date pDate) {
    return new SimpleDateFormat(format, Locale.getDefault()).format(pDate);
  }

  public static String getDateInRechargeDateFormat(Long dateTimestamp) {
    Date date = new Date(dateTimestamp);
    return getDateString(ProjectConstants.TIME_FORMAT, date) + " on " + getDateString(
        ProjectConstants.DATE_FORMAT, date);
  }
}
