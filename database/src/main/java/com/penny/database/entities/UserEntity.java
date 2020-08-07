package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "UserEntity")
public class UserEntity {

  @PrimaryKey(autoGenerate = true)
  private int UserID;

  @SerializedName("id")
  @ColumnInfo(name = "id")
  private long serverUserID;

  @SerializedName("userName")
  @ColumnInfo(name = "userName")
  private String UserName;

  public int getUserID() {
    return UserID;
  }

  public void setUserID(int userID) {
    UserID = userID;
  }

  public long getServerUserID() {
    return serverUserID;
  }

  public void setServerUserID(long serverUserID) {
    this.serverUserID = serverUserID;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }
}
