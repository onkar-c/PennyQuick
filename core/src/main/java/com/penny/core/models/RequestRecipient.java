package com.penny.core.models;

import java.io.Serializable;

public class RequestRecipient implements Serializable {

  private String bankCode;
  private String account;
  private Boolean ifscRequired;
  private String custId;
  private String ifsc;
  private String recipientName;
  private String mobile;

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public Boolean getIfscRequired() {
    return ifscRequired;
  }

  public void setIfscRequired(Boolean ifscRequired) {
    this.ifscRequired = ifscRequired;
  }

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public String getIfsc() {
    return ifsc;
  }

  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }

  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
