package com.penny.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "Recipient")
public class Recipient implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private Integer id;

  @SerializedName("account_type")
  @ColumnInfo(name = "account_type")
  private String account_type;

  @SerializedName("ifsc_status")
  @ColumnInfo(name = "ifsc_status")
  private Integer ifsc_status;

  @SerializedName("is_self_account")
  @ColumnInfo(name = "is_self_account")
  private String is_self_account;

  @SerializedName("channel")
  @ColumnInfo(name = "channel")
  private Integer channel;

  @SerializedName("is_imps_scheduled")
  @ColumnInfo(name = "is_imps_scheduled")
  private Integer is_imps_scheduled;

  @SerializedName("recipient_id_type")
  @ColumnInfo(name = "recipient_id_type")
  private String recipient_id_type;

  @SerializedName("imps_inactive_reason")
  @ColumnInfo(name = "imps_inactive_reason")
  private String imps_inactive_reason;

  @SerializedName("allowed_channel")
  @ColumnInfo(name = "allowed_channel")
  private Integer allowed_channel;

  @SerializedName("is_verified")
  @ColumnInfo(name = "is_verified")
  private Integer is_verified;

  @SerializedName("bank")
  @ColumnInfo(name = "bank")
  private String bank;

  @SerializedName("is_otp_required")
  @ColumnInfo(name = "is_otp_required")
  private String is_otp_required;

  @SerializedName("recipient_mobile")
  @ColumnInfo(name = "recipient_mobile")
  private String recipient_mobile;

  @SerializedName("recipient_name")
  @ColumnInfo(name = "recipient_name")
  private String recipient_name;

  @SerializedName("ifsc")
  @ColumnInfo(name = "ifsc")
  private String ifsc;

  @SerializedName("account")
  @ColumnInfo(name = "account")
  private String account;

  @SerializedName("recipient_id")
  @ColumnInfo(name = "recipient_id")
  private Long recipient_id;

  @SerializedName("is_rblbc_recipient")
  @ColumnInfo(name = "is_rblbc_recipient")
  private Integer is_rblbc_recipient;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccount_type() {
    return account_type;
  }

  public void setAccount_type(String account_type) {
    this.account_type = account_type;
  }

  public Integer getIfsc_status() {
    return ifsc_status;
  }

  public void setIfsc_status(Integer ifsc_status) {
    this.ifsc_status = ifsc_status;
  }

  public String getIs_self_account() {
    return is_self_account;
  }

  public void setIs_self_account(String is_self_account) {
    this.is_self_account = is_self_account;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public Integer getIs_imps_scheduled() {
    return is_imps_scheduled;
  }

  public void setIs_imps_scheduled(Integer is_imps_scheduled) {
    this.is_imps_scheduled = is_imps_scheduled;
  }

  public String getRecipient_id_type() {
    return recipient_id_type;
  }

  public void setRecipient_id_type(String recipient_id_type) {
    this.recipient_id_type = recipient_id_type;
  }

  public String getImps_inactive_reason() {
    return imps_inactive_reason;
  }

  public void setImps_inactive_reason(String imps_inactive_reason) {
    this.imps_inactive_reason = imps_inactive_reason;
  }

  public Integer getAllowed_channel() {
    return allowed_channel;
  }

  public void setAllowed_channel(Integer allowed_channel) {
    this.allowed_channel = allowed_channel;
  }

  public Integer getIs_verified() {
    return is_verified;
  }

  public void setIs_verified(Integer is_verified) {
    this.is_verified = is_verified;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public String getIs_otp_required() {
    return is_otp_required;
  }

  public void setIs_otp_required(String is_otp_required) {
    this.is_otp_required = is_otp_required;
  }

  public String getRecipient_mobile() {
    return recipient_mobile;
  }

  public void setRecipient_mobile(String recipient_mobile) {
    this.recipient_mobile = recipient_mobile;
  }

  public String getRecipient_name() {
    return recipient_name;
  }

  public void setRecipient_name(String recipient_name) {
    this.recipient_name = recipient_name;
  }

  public String getIfsc() {
    return ifsc;
  }

  public void setIfsc(String ifsc) {
    this.ifsc = ifsc;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public Long getRecipient_id() {
    return recipient_id;
  }

  public void setRecipient_id(Long recipient_id) {
    this.recipient_id = recipient_id;
  }

  public Integer getIs_rblbc_recipient() {
    return is_rblbc_recipient;
  }

  public void setIs_rblbc_recipient(Integer is_rblbc_recipient) {
    this.is_rblbc_recipient = is_rblbc_recipient;
  }
}
