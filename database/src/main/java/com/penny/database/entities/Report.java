package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "Report")
public class Report implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("txnId")
  @ColumnInfo(name = "txnId")
  private String transaction_id;

  @SerializedName("description")
  @ColumnInfo(name = "description")
  private String description;

  @SerializedName("balance")
  @ColumnInfo(name = "balance")
  private float balance;

  @SerializedName("type")
  @ColumnInfo(name = "type")
  private String reportType;

  @SerializedName("transType")
  @ColumnInfo(name = "transType")
  private String transactionType;

  @SerializedName("amount")
  @ColumnInfo(name = "amount")
  private float transactionAmount;

  @SerializedName("datetime")
  @ColumnInfo(name = "datetime")
  private String date;

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
