package com.penny.core.models;

import com.google.gson.annotations.Expose;

public class DateFormatModel {

  @Expose(deserialize = false, serialize = false)
  private String id;
  private String month;
  private String year;
  @Expose(deserialize = false, serialize = false)
  private String monthDisplay;
  @Expose(deserialize = false, serialize = false)
  private boolean isChecked = false;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getMonthDisplay() {
    return monthDisplay;
  }

  public void setMonthDisplay(String monthDisplay) {
    this.monthDisplay = monthDisplay;
  }

  public boolean isChecked() {
    return isChecked;
  }

  public void setChecked(boolean checked) {
    isChecked = checked;
  }
}
