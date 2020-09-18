package com.penny.quick.ui.activities.splash;

import androidx.lifecycle.ViewModel;
import com.penny.core.repositories.OperatorsRepository;
import com.penny.database.entities.Operators;
import java.util.List;

class SplashActivityViewModel extends ViewModel {

  SplashActivityViewModel() {
    super();
  }

  public void saveOperators(List<Operators> operatorsList) {
    new OperatorsRepository().saveOperators(operatorsList);
  }
}
