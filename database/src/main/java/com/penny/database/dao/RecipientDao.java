package com.penny.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.Recipient;
import java.util.List;

@Dao
public abstract class RecipientDao extends AbstractBaseDao<Recipient> {

  @Query("select * from Recipient")
  public abstract List<Recipient> getRecipients();

}
