package com.penny.quick.ui.activities.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.penny.database.AppDatabase;
import com.penny.database.entities.User;

public class ProfileActivityViewModel extends ViewModel {

  ProfileActivityViewModel() { super(); }

  public LiveData<User> getProfileData() {
    return AppDatabase.getInstance().getUserEntityDao().getUser();
  }
}
