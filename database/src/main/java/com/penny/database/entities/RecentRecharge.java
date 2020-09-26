package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "RecentRecharge")
public class RecentRecharge implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("type")
  @ColumnInfo(name = "type")
  private String companyType;

  @SerializedName("mobile")
  @ColumnInfo(name = "mobile")
  private String customerId;

  @SerializedName("txnId")
  @ColumnInfo(name = "txnId")
  private String transactionId;

  @SerializedName("datetime")
  @ColumnInfo(name = "datetime")
  private Long date;

  @SerializedName("status")
  @ColumnInfo(name = "status")
  private String status;

  @SerializedName("amount")
  @ColumnInfo(name = "amount")
  private String amount;

  @SerializedName("operator")
  @ColumnInfo(name = "operator")
  private String operator;

  @SerializedName("displayName")
  @ColumnInfo(name = "displayName")
  private String displayName;

  public String getCompanyType() {
    return companyType;
  }

  public void setCompanyType(String companyType) {
    this.companyType = companyType;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
