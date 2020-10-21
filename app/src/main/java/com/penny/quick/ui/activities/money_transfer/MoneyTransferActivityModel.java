package com.penny.quick.ui.activities.money_transfer;

import dagger.Module;
import dagger.Provides;

@Module
public class MoneyTransferActivityModel {

  @Provides
  MoneyTransferActivityViewModel MoneyTransferActivityModel() {
    return new MoneyTransferActivityViewModel();
  }
}
