package com.penny.quick.ui.activities.money_transfer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.WorkInfo;
import com.penny.core.repositories.MoneyTransferRepository;
import com.penny.database.entities.BankDetails;
import com.penny.database.entities.Recipient;
import java.util.List;

public class MoneyTransferActivityViewModel extends ViewModel {

  MoneyTransferActivityViewModel() {
    super();
  }

  public LiveData<WorkInfo> verifyMobileNumber(String mobile) {
    return new MoneyTransferRepository()
        .getVerifyMobileNumberWorkManager(mobile);
  }

  public LiveData<WorkInfo> enrollMobileNumber(String mobile, String name) {
    return new MoneyTransferRepository()
        .getEnrollMobileNumberWorkManager(mobile, name);
  }

  public LiveData<WorkInfo> transferMoney(String name, String number,
      String recipeientID, String ifsc, String account, String customerID, String ammount,
      String doc_id_type, String doc_id, String pincode, String transactionType) {
    return new MoneyTransferRepository()
        .getTransferMoneyWorkManager(name, number,
            recipeientID, ifsc, account, customerID, ammount,
            doc_id_type, doc_id, pincode, transactionType);
  }

  public LiveData<WorkInfo> verifyOtp(String mobile, String otp) {
    return new MoneyTransferRepository()
        .getVerifyOTPWorkManager(mobile, otp);
  }

  public LiveData<WorkInfo> fetchRecipient(String mobile) {
    return new MoneyTransferRepository()
        .getRecipientListWorkManager(mobile);
  }

  public LiveData<WorkInfo> resendOtp(String mobile) {
    return new MoneyTransferRepository()
        .getResendOTPWorkManager(mobile);
  }

  public List<Recipient> getRecipients() {
    return new MoneyTransferRepository().getRecipients();
  }

  public List<BankDetails> getBanks() {
    return new MoneyTransferRepository().getBanks();
  }

  public LiveData<WorkInfo> addRecipient(String bankCode, String accountNumber,
      String customerID, String ifsc, String userName, String mobileNumber) {
    return new MoneyTransferRepository()
        .addRecipientWorkManager(bankCode, accountNumber, customerID, ifsc, userName, mobileNumber);
  }
}
