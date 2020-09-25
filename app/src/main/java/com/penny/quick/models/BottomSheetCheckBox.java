package com.penny.quick.models;

public class BottomSheetCheckBox {
  private String id;
  private String title;
  private boolean isChecked;
  private String actualName;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isChecked() {
    return isChecked;
  }

  public void setChecked(boolean checked) {
    isChecked = checked;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getActualName() {
    return actualName;
  }

  public void setActualName(String actualName) {
    this.actualName = actualName;
  }
}
