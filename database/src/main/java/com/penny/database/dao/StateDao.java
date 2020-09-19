package com.penny.database.dao;

import androidx.room.Dao;
import androidx.room.Query;
import com.penny.database.AbstractBaseDao;
import com.penny.database.entities.Operators;
import com.penny.database.entities.State;
import java.util.List;

@Dao
public abstract class StateDao extends AbstractBaseDao<State> {

}
