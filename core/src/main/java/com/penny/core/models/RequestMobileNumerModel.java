package com.penny.core.models;

import com.google.gson.annotations.SerializedName;

public class RequestMobileNumerModel {

  @SerializedName("mobile")
  private String mobile;

  @SerializedName("name")
  private String name;

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
}
