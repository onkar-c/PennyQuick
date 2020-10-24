package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "BankDetails")
public class BankDetails implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("bankName")
  @ColumnInfo(name = "bankName")
  private String bankName;

  @SerializedName("bankId")
  @ColumnInfo(name = "bankId")
  private String bankId;

  @SerializedName("bankCode")
  @ColumnInfo(name = "bankCode")
  private String bankCode;

  @SerializedName("neftAvailablity")
  @ColumnInfo(name = "neftAvailablity")
  private String neftAvailablity;

  @SerializedName("impsAvailablity")
  @ColumnInfo(name = "impsAvailablity")
  private String impsAvailablity;

  @SerializedName("accountVerification")
  @ColumnInfo(name = "accountVerification")
  private String accountVerification;

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankId() {
    return bankId;
  }

  public void setBankId(String bankId) {
    this.bankId = bankId;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getNeftAvailablity() {
    return neftAvailablity;
  }

  public void setNeftAvailablity(String neftAvailablity) {
    this.neftAvailablity = neftAvailablity;
  }

  public String getImpsAvailablity() {
    return impsAvailablity;
  }

  public void setImpsAvailablity(String impsAvailablity) {
    this.impsAvailablity = impsAvailablity;
  }

  public String getAccountVerification() {
    return accountVerification;
  }

  public void setAccountVerification(String accountVerification) {
    this.accountVerification = accountVerification;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
