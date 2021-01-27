package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "Dispute")
public class Dispute implements Serializable {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("txnId")
  @ColumnInfo(name = "txnId")
  private String transactionId;

  @SerializedName("status")
  @ColumnInfo(name = "status")
  private String status;

  @SerializedName("datetime")
  @ColumnInfo(name = "datetime")
  private Long date;

  @SerializedName("message")
  @ColumnInfo(name = "message")
  private String message;

  @SerializedName("complain_type")
  @ColumnInfo(name = "complain_type")
  private String complain_type;

  @SerializedName("complainsolve_date")
  @ColumnInfo(name = "complainsolve_date")
  private Long complainsolve_date;

  @SerializedName("response_message")
  @ColumnInfo(name = "response_message")
  private String response_message;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getComplain_type() {
    return complain_type;
  }

  public void setComplain_type(String complain_type) {
    this.complain_type = complain_type;
  }

  public Long getComplainsolve_date() {
    return complainsolve_date;
  }

  public void setComplainsolve_date(Long complainsolve_date) {
    this.complainsolve_date = complainsolve_date;
  }

  public String getResponse_message() {
    return response_message;
  }

  public void setResponse_message(String response_message) {
    this.response_message = response_message;
  }
}
