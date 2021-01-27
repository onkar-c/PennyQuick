package com.penny.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.Operators;
import java.util.List;

@Dao
public abstract class OperatorsDao extends AbstractBaseDao<Operators> {

  @Query("select * from operators where service_name =:type ")
  public abstract List<Operators> getOperatorsByType(String type);

  @Query("select company_name from operators where provider =:type")
  public abstract String getOperatorsNameByType(String type);

  @Query("select * from operators where provider =:type")
  public abstract List<Operators> getOperatorsByProvider(String type);
}
