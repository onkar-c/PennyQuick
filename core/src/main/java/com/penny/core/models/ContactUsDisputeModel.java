package com.penny.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ContactUsDisputeModel implements Serializable {

  @SerializedName("name")
  private String name;
  @SerializedName("mobile")
  private String mobile;
  @SerializedName("transactionId")
  private String transactionId;
  @SerializedName("message")
  private String message;
  @SerializedName("subject")
  private String subject;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }
}
