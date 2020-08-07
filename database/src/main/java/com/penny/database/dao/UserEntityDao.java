package com.penny.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.UserEntity;
import java.util.List;

@Dao
public abstract class UserEntityDao extends AbstractBaseDao<UserEntity> {

  @Query("select * from UserEntity where UserName = :userName  ORDER BY UserName asc")
  public abstract List<UserEntity> getUserByUserName(String userName);

}
