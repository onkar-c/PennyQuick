package com.penny.quick.models;

import java.util.List;

public class TabModel {
  private String title;
  private List<PlanModel> planModels;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<PlanModel> getPlanModels() {
    return planModels;
  }

  public void setPlanModels(List<PlanModel> planModels) {
    this.planModels = planModels;
  }
}
