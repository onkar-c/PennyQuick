package com.penny.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.RecentRecharge;
import java.util.List;

@Dao
public abstract class RecentRechargeDao extends AbstractBaseDao<RecentRecharge> {

  @Query("select * from RecentRecharge order by datetime desc")
  public abstract LiveData<List<RecentRecharge>> getRecentRecharges();

}
