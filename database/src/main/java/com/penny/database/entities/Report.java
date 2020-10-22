package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "Report")
public class Report implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @ColumnInfo(name = "reportType")
  private String reportType;

  @ColumnInfo(name = "transactionType")
  private String transactionType;

  @ColumnInfo(name = "transaction_id")
  private String transaction_id;

  @ColumnInfo(name = "transactionAmount")
  private float transactionAmount;

  @ColumnInfo(name = "date")
  private String date;

  @ColumnInfo(name = "description")
  private String description;

  @ColumnInfo(name = "balance")
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

  public String getTransaction_id() {
    return transaction_id;
  }

  public void setTransaction_id(String transaction_id) {
    this.transaction_id = transaction_id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
