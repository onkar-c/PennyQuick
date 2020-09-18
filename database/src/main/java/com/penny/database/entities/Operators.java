package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "operators")
public class Operators implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("company_name")
  @ColumnInfo(name = "company_name")
  private String company_name;

  @SerializedName("provider")
  @ColumnInfo(name = "provider")
  private String provider;

  @SerializedName("service_name")
  @ColumnInfo(name = "service_name")
  private String service_name;

  @SerializedName("display_name")
  @ColumnInfo(name = "display_name")
  private String display_name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCompany_name() {
    return company_name;
  }

  public void setCompany_name(String company_name) {
    this.company_name = company_name;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getService_name() {
    return service_name;
  }

  public void setService_name(String service_name) {
    this.service_name = service_name;
  }

  public String getDisplay_name() {
    return display_name;
  }

  public void setDisplay_name(String display_name) {
    this.display_name = display_name;
  }
}
