package com.penny.database.dao;

public class Report {

  private String reportType;
  private String transactionType;
  private float transactionAmount;
  private String date;
  private String description;
  private float balance;

  public String getReportType() {
    return reportType;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

  public float getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(float transactionAmount) {
    this.transactionAmount = transactionAmount;
  }
}
