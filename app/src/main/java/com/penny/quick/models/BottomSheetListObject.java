package com.penny.quick.models;

import com.penny.quick.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BottomSheetListObject {

  private int id;
  private int iconId;
  private int iconImage;
  private String name;

  public BottomSheetListObject(int id, int iconId, int iconImage, String name) {
    this.id = id;
    this.iconId = iconId;
    this.iconImage = iconImage;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getIconId() {
    return iconId;
  }

  public void setIconId(int iconId) {
    this.iconId = iconId;
  }

  public int getIconImage() {
    return iconImage;
  }

  public void setIconImage(int iconImage) {
    this.iconImage = iconImage;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static List<BottomSheetListObject> getObjectList(){
    List<BottomSheetListObject> operatorList = Arrays.asList(new BottomSheetListObject(1, 1,R.drawable.airtel,"Airtel"),
        new BottomSheetListObject(2, 2,R.drawable.airtel,"Idea") );

    return operatorList;
  }
}
