package com.penny.database.entities;

import com.google.gson.annotations.SerializedName;

public class User {

  @SerializedName("user_id")
  private String userId;
  @SerializedName("mobile_no")
  private String mobileNumber;
  @SerializedName("business_name")
  private String businessName;
  @SerializedName("contact_person")
  private String contactPerson;
  @SerializedName("usertype_name")
  private String userTypeName;
  @SerializedName("img")
  private String imageUrl;
  @SerializedName("authkey")
  private String authKey;
  @SerializedName("total_balance")
  private String totalBalance;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getBusinessName() {
    return businessName;
  }

  public void setBusinessName(String businessName) {
    this.businessName = businessName;
  }

  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  public String getUserTypeName() {
    return userTypeName;
  }

  public void setUserTypeName(String userTypeName) {
    this.userTypeName = userTypeName;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getAuthKey() {
    return authKey;
  }

  public void setAuthKey(String authKey) {
    this.authKey = authKey;
  }

  public String getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(String totalBalance) {
    this.totalBalance = totalBalance;
  }
}
