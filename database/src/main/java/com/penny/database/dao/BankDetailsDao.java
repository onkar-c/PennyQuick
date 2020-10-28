package com.penny.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.BankDetails;
import java.util.List;

@Dao
public abstract class BankDetailsDao extends AbstractBaseDao<BankDetails> {

  @Query("select * from BankDetails")
  public abstract List<BankDetails> getBanks();
}
