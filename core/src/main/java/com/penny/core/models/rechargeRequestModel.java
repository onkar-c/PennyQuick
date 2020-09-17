package com.penny.core.models;

public class rechargeRequestModel {

  private String number;
  private float amount;
  private String operator;
  private String circle;
  private String service;

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getCircle() {
    return circle;
  }

  public void setCircle(String circle) {
    this.circle = circle;
  }

  public String getService() {
    return service;
  }

  public void setService(String service) {
    this.service = service;
  }
}
