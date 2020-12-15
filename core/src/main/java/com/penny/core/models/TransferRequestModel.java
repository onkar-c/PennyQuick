package com.penny.core.models;

public class TransferRequestModel {
  private String recipientId;
  private String ifsc;
  private String account;
  private String mobile;
  private String name;
  private String custId;
  private String txtAmount;
  private String merchantDocumentIdType;
  private String merchantDocumentId;
  private String pincode;
  private String transactionType;

  public String getRecipientId() {
    return recipientId;
  }

  public void setRecipientId(String recipientId) {
    this.recipientId = recipientId;
  }

  public String getIfsc() {
    return ifsc;
  }

  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public String getTxtAmount() {
    return txtAmount;
  }

  public void setTxtAmount(String txtAmount) {
    this.txtAmount = txtAmount;
  }

  public String getMerchantDocumentIdType() {
    return merchantDocumentIdType;
  }

  public void setMerchantDocumentIdType(String merchantDocumentIdType) {
    this.merchantDocumentIdType = merchantDocumentIdType;
  }

  public String getMerchantDocumentId() {
    return merchantDocumentId;
  }

  public void setMerchantDocumentId(String merchantDocumentId) {
    this.merchantDocumentId = merchantDocumentId;
  }

  public String getPincode() {
    return pincode;
  }

  public void setPincode(String pincode) {
    this.pincode = pincode;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }
}
