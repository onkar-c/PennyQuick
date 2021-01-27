package com.penny.quick.ui.activities.contact_us_dispute;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.UserRepository;
import com.penny.database.entities.Dispute;
import java.util.List;

public class ContactUsDisputeViewModel extends ViewModel {

  ContactUsDisputeViewModel() {
    super();
  }

  public LiveData<WorkInfo> sendData(String name, String mobileNumber, String subject,
      String message, boolean isDispute) {
    return new UserRepository()
        .getContactUsDisputeWorkManager(name, mobileNumber, subject, message, isDispute);
  }

  public LiveData<WorkInfo> getDisputeHistory() {
    return new UserRepository()
        .getDisputeHistoryWorkManager();
  }

  public LiveData<List<Dispute>> getDisputeHistoryObserver() {
    return new UserRepository()
        .getDisputeHistory();
  }
}
