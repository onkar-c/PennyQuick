package com.penny.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class ReportsRequest implements Serializable {

  @SerializedName("type")
  List<String> type;
  @SerializedName("monthFilter")
  List<MonthRequest> monthFilter;

  public List<String> getType() {
    return type;
  }

  public void setType(List<String> type) {
    this.type = type;
  }

  public List<MonthRequest> getMonthFilter() {
    return monthFilter;
  }

  public void setMonthFilter(List<MonthRequest> monthFilter) {
    this.monthFilter = monthFilter;
  }
}
