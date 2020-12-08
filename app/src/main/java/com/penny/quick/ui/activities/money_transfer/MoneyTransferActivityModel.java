package com.penny.quick.ui.activities.money_transfer;

import dagger.Module;
import dagger.Provides;

@Module
public class MoneyTransferActivityModel {

  @Provides
  MoneyTransferActivityViewModel getMoneyTransferActivityModel() {
    return new MoneyTransferActivityViewModel();
  }
}
