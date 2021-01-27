package com.penny.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.Dispute;
import java.util.List;

@Dao
public abstract class DisputeDao extends AbstractBaseDao<Dispute> {

  @Query("select * from Dispute order by datetime desc")
  public abstract LiveData<List<Dispute>> getDisputes();
}
