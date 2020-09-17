package com.penny.core.models;

import com.google.gson.annotations.SerializedName;
import com.penny.database.entities.RecentRecharge;
import com.penny.database.entities.User;
import java.io.Serializable;
import java.util.List;

public class JsonResponse implements Serializable {

  @SerializedName("recharge")
  List<RecentRecharge> recentRecharges;
  @SerializedName("message")
  private
  String message;
  @SerializedName("status")
  private
  String status;
  @SerializedName("user")
  private User user;

  public List<RecentRecharge> getRecentRecharges() {
    return recentRecharges;
  }

  public void setRecentRecharges(List<RecentRecharge> recentRecharges) {
    this.recentRecharges = recentRecharges;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String isStatus() {
    return status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
