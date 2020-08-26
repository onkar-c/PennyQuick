package com.penny.core.models;

public class ChangePasswordRequestModel {
 private String mobile;
 private String otp;
 private String old_password;
 private String password;

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getOtp() {
    return otp;
  }

  public void setOtp(String otp) {
    this.otp = otp;
  }

  public String getOld_password() {
    return old_password;
  }

  public void setOld_password(String old_password) {
    this.old_password = old_password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
