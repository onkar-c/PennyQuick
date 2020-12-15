package com.penny.core.models;

public class UserTransferModel {

  private String customer_id_type;
  private long bc_available_limit;
  private String mobile;
  private String used_limit;
  private String total_limit;
  private String available_limit;
  private String balance;
  private String state_desc;
  private String name;
  private String currency;
  private String state;
  private long wallet_available_limit;
  private String customer_id;

  public String getCustomer_id_type() {
    return customer_id_type;
  }

  public void setCustomer_id_type(String customer_id_type) {
    this.customer_id_type = customer_id_type;
  }

  public long getBc_available_limit() {
    return bc_available_limit;
  }

  public void setBc_available_limit(long bc_available_limit) {
    this.bc_available_limit = bc_available_limit;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getUsed_limit() {
    return used_limit;
  }

  public void setUsed_limit(String used_limit) {
    this.used_limit = used_limit;
  }

  public String getTotal_limit() {
    return total_limit;
  }

  public void setTotal_limit(String total_limit) {
    this.total_limit = total_limit;
  }

  public String getAvailable_limit() {
    return available_limit;
  }

  public void setAvailable_limit(String available_limit) {
    this.available_limit = available_limit;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getState_desc() {
    return state_desc;
  }

  public void setState_desc(String state_desc) {
    this.state_desc = state_desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public long getWallet_available_limit() {
    return wallet_available_limit;
  }

  public void setWallet_available_limit(long wallet_available_limit) {
    this.wallet_available_limit = wallet_available_limit;
  }

  public String getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(String customer_id) {
    this.customer_id = customer_id;
  }
}
