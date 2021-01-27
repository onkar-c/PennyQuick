package com.penny.core.models;

import com.google.gson.annotations.SerializedName;
import com.penny.database.entities.BankDetails;
import com.penny.database.entities.Dispute;
import com.penny.database.entities.Operators;
import com.penny.database.entities.RecentRecharge;
import com.penny.database.entities.Recipient;
import com.penny.database.entities.Report;
import com.penny.database.entities.User;
import java.io.Serializable;
import java.util.List;

public class JsonResponse implements Serializable {

  @SerializedName("recentRecharges")
  private List<RecentRecharge> recentRecharges;
  @SerializedName("message")
  private
  String message;
  @SerializedName("status")
  private
  String status;
  @SerializedName("user")
  private User user;

  @SerializedName("userinfo")
  private UserTransferModel userTransferModel;

  @SerializedName("recharge")
  private TransactionResponse recharge;

  @SerializedName("transfer")
  private TransactionResponse transfer;

  @SerializedName("providerList")
  private List<Operators> providerList;

  @SerializedName("reportTransactions")
  private List<Report> reports;

  @SerializedName("list")
  private List<BankDetails> bankDetailsList;

  @SerializedName("disputeList")
  private List<Dispute> disputeList;

  @SerializedName("trans_type")
  private int trans_type;

  @SerializedName("recipient_list")
  private List<Recipient> recipientList;

  public List<RecentRecharge> getRecentRecharges() {
    return recentRecharges;
  }

  public void setRecentRecharges(List<RecentRecharge> recentRecharges) {
    this.recentRecharges = recentRecharges;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String isStatus() {
    return status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public TransactionResponse getRecharge() {
    return recharge;
  }

  public void setRecharge(TransactionResponse recharge) {
    this.recharge = recharge;
  }

  public List<Operators> getProviderList() {
    return providerList;
  }

  public void setProviderList(List<Operators> providerList) {
    this.providerList = providerList;
  }

  public List<Report> getReports() {
    return reports;
  }

  public void setReports(List<Report> reports) {
    this.reports = reports;
  }

  public List<BankDetails> getBankDetailsList() {
    return bankDetailsList;
  }

  public void setBankDetailsList(List<BankDetails> bankDetailsList) {
    this.bankDetailsList = bankDetailsList;
  }

  public int getTrans_type() {
    return trans_type;
  }

  public void setTrans_type(int trans_type) {
    this.trans_type = trans_type;
  }

  public List<Recipient> getRecipientList() {
    return recipientList;
  }

  public void setRecipientList(List<Recipient> recipientList) {
    this.recipientList = recipientList;
  }

  public UserTransferModel getUserTransferModel() {
    return userTransferModel;
  }

  public void setUserTransferModel(UserTransferModel userTransferModel) {
    this.userTransferModel = userTransferModel;
  }

  public TransactionResponse getTransfer() {
    return transfer;
  }

  public void setTransfer(TransactionResponse transfer) {
    this.transfer = transfer;
  }

  public List<Dispute> getDisputeList() {
    return disputeList;
  }
}
