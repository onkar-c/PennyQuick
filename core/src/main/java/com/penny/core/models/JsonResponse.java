package com.penny.core.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class JsonResponse implements Serializable {

  @SerializedName("message")
  private
  String message;

  @SerializedName("status")
  private
  boolean status;

  @SerializedName("token")
  private String jwtToken;


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}
