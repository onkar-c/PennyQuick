package com.penny.core.repositories;

import com.penny.database.AppDatabase;
import com.penny.database.entities.Operators;
import java.util.List;

public class OperatorsRepository extends BaseRepository  {

  public void saveOperators(List<Operators> operatorsList) {
    AppDatabase.getInstance().getOperatorsDao().insert(operatorsList);
  }

  public List<Operators> getOperatorsByType(String type) {
    return AppDatabase.getInstance().getOperatorsDao().getOperatorsByType(type);
  }

  public String getOperatorsNameByType(String type) {
    return AppDatabase.getInstance().getOperatorsDao().getOperatorsNameByType(type);
  }
}
