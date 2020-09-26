package com.penny.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "User")
public class User {

  @PrimaryKey
  @SerializedName("user_id")
  @ColumnInfo(name = "userId")
  @NonNull
  private String userId;

  @SerializedName("mobile_no")
  @ColumnInfo(name = "mobileNumber")
  private String mobileNumber;

  @SerializedName("business_name")
  @ColumnInfo(name = "businessName")
  private String businessName;

  @SerializedName("contact_person")
  @ColumnInfo(name = "contactPerson")
  private String contactPerson;

  @SerializedName("usertype_name")
  @ColumnInfo(name = "userTypeName")
  private String userTypeName;

  @SerializedName("img")
  @ColumnInfo(name = "imageUrl")
  private String imageUrl;

  @SerializedName("authkey")
  @ColumnInfo(name = "authKey")
  private String authKey;

  @SerializedName("total_balance")
  @ColumnInfo(name = "totalBalance")
  private String totalBalance;

  @SerializedName("email")
  @ColumnInfo(name = "email")
  private String email;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
