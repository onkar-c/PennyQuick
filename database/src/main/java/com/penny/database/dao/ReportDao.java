package com.penny.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.Report;
import java.util.List;

@Dao
public abstract class ReportDao extends AbstractBaseDao<Report> {

  @Query("select * from Report")
  public abstract LiveData<List<Report>> getDisputeHistory();

}
