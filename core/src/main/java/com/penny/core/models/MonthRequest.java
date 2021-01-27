package com.penny.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MonthRequest implements Serializable {

  @SerializedName("month")
  private int month;
  @SerializedName("year")
  private long year;

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public long getYear() {
    return year;
  }

  public void setYear(long year) {
    this.year = year;
  }
}
