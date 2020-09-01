package com.penny.quick.ui.activities.transaction_status;

import dagger.Module;
import dagger.Provides;

@Module
public class TransactionStatusActivityModel {

  @Provides
  TransactionStatusActivityViewModel getTransactionStatusActivityModel() {
    return new TransactionStatusActivityViewModel();
  }
}
