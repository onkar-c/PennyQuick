package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "state")
public class State implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("State Id")
  @ColumnInfo(name = "stateId")
  private String StateId;

  @SerializedName("State Name")
  @ColumnInfo(name = "displayName")
  private String displayName;
  @SerializedName("Circle")
  @ColumnInfo(name = "stateCode")
  private String stateCode;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public String getStateId() {
    return StateId;
  }

  public void setStateId(String stateId) {
    StateId = stateId;
  }
}
