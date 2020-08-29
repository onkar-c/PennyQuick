package com.penny.quick.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PlanModel implements Parcelable {
  private Float talktime;
  private String data;
  private String validity;
  private float amount;
  private String message1;
  private String message2;
  private String message3;

  public PlanModel() {
  }

  protected PlanModel(Parcel in) {
    if (in.readByte() == 0) {
      talktime = null;
    } else {
      talktime = in.readFloat();
    }
    data = in.readString();
    validity = in.readString();
    amount = in.readFloat();
    message1 = in.readString();
    message2 = in.readString();
    message3 = in.readString();
  }

  public static final Creator<PlanModel> CREATOR = new Creator<PlanModel>() {
    @Override
    public PlanModel createFromParcel(Parcel in) {
      return new PlanModel(in);
    }

    @Override
    public PlanModel[] newArray(int size) {
      return new PlanModel[size];
    }
  };

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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    if (talktime == null) {
      parcel.writeByte((byte) 0);
    } else {
      parcel.writeByte((byte) 1);
      parcel.writeFloat(talktime);
    }
    parcel.writeString(data);
    parcel.writeString(validity);
    parcel.writeFloat(amount);
    parcel.writeString(message1);
    parcel.writeString(message2);
    parcel.writeString(message3);
  }
}
