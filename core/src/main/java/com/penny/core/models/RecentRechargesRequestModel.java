package com.penny.core.models;

import java.util.List;

public class RecentRechargesRequestModel {

  private List<String> type;
  private List<String> statusType;
  private int start;
  private int limit;
  private List<DateFormatModel> monthFilter;

  public List<String> getType() {
    return type;
  }

  public void setType(List<String> type) {
    this.type = type;
  }

  public List<String> getStatusType() {
    return statusType;
  }

  public void setStatusType(List<String> statusType) {
    this.statusType = statusType;
  }

  public int getStart() {
    return start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public List<DateFormatModel> getMonthFilter() {
    return monthFilter;
  }

  public void setMonthFilter(List<DateFormatModel> monthFilter) {
    this.monthFilter = monthFilter;
  }
}
