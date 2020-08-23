package com.penny.core.models;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Bagwan Akib on 26-01-2020. Nitor Infotech Project : RPS
 */
public class ErrorJsonResponse {

  @SerializedName("message")
  private String message;

  @SerializedName("code")
  private String code;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
