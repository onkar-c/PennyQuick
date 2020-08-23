package com.penny.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.User;
import java.util.List;

@Dao
public abstract class UserEntityDao extends AbstractBaseDao<User> {

  @Query("select * from User where userId = :id ")
  public abstract List<User> getUserByUserId(String id);

}
