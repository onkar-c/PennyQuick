package com.penny.quick.ui.activities.dash_board;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.penny.core.repositories.UserRepository;
import com.penny.database.entities.User;

public class DashBoardActivityViewModel extends ViewModel {

  DashBoardActivityViewModel(){super();}

  public LiveData<User> getUser() {
    return new UserRepository().getUser();
  }
}
