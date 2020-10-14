package com.penny.quick.ui.activities.contact_us_dispute;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactUsDisputeActivityModel {

  @Provides
  ContactUsDisputeViewModel provideContactUsDisputeViewModel() {
    return new ContactUsDisputeViewModel();
  }
}
