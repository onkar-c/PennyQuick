package com.penny.quick.models;

public class PlanModel {
  private Float talktime;
  private String data;
  private String validity;
  private float amount;
  private String message1;
  private String message2;
  private String message3;

  public Float getTalktime() {
    return talktime;
  }

  public void setTalktime(Float talktime) {
    this.talktime = talktime;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getValidity() {
    return validity;
  }

  public void setValidity(String validity) {
    this.validity = validity;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public String getMessage1() {
    return message1;
  }

  public void setMessage1(String message1) {
    this.message1 = message1;
  }

  public String getMessage2() {
    return message2;
  }

  public void setMessage2(String message2) {
    this.message2 = message2;
  }

  public String getMessage3() {
    return message3;
  }

  public void setMessage3(String message3) {
    this.message3 = message3;
  }
}
