package com.penny.core.repositories;

import com.penny.database.AppDatabase;
import com.penny.database.entities.State;
import java.util.List;

public class StatesRepository extends BaseRepository {

  public void saveStates(List<State> statesList) {
    AppDatabase.getInstance().getStatesDao().insert(statesList);
  }

  public List<State> getStates() {
    return AppDatabase.getInstance().getStatesDao().get();
  }
}
