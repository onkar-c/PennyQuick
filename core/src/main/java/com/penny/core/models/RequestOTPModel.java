package com.penny.core.models;

import com.google.gson.annotations.SerializedName;

public class RequestOTPModel {

  @SerializedName("mobile")
  private String mobile;

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
}
