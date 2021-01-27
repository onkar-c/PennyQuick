package com.penny.core;

/**
 * Created by Onkar Chopade on 16-04-2020. Nitor Infotech Project : rps-mobile
 */
public class APITags {

  public static final String API_ID = "worker_id";
  private static final String API_LOADING_MESSAGE = "Loading..";
  private static final String API_PLEASE_WAIT_MESSAGE = "Please Wait";
  public static final String SUCCESS = "success";

  public static final int API_LOGIN = 1;
  private static final String API_LOGIN_TITLE = "Login";

  public static final int API_REQUEST_OTP = 2;
  private static final String API_REQUEST_OTP_TITTLE = "OTP";
  private static final String API_REQUEST_OTP_MESSAGE = "Requesting OTP..";

  public static final int API_VERIFY_OTP = 3;
  private static final String API_VERIFY_OTP_TITTLE = "OTP";
  private static final String API_VERIFY_OTP_MESSAGE = "Verifying OTP..";

  public static final int API_CHANGE_PASSWORD = 4;
  private static final String API_CHANGE_PASSWORD_TITTLE = "OTP";
  private static final String API_CHANGE_PASSWORD_MESSAGE = "Verifying OTP..";

  public static final int API_USER_INFO = 5;
  private static final String API_USER_INFO_TITTLE = "User info";
  private static final String API_USER_INFO_MESSAGE = "Fetching User info..";



  public static final int API_MOBILE_RECHARGE = 6;
  private static final String API_MOBILE_RECHARGE_TITTLE = "Transaction in progress";
  private static final String API_MOBILE_RECHARGE_MESSAGE = "Please wait";


  public static final int API_RECHARGE_STATUS = 7;
  private static final String API_RECHARGE_STATUS_TITTLE = "Update customer";
  private static final String API_RECHARGE_STATUS_MESSAGE = "Updating Customer details";


  public static final int API_RECENT_RECHARGES = 8;
  private static final String API_RECENT_RECHARGES_TITTLE = "Fetching details";
  private static final String API_RECENT_RECHARGES_MESSAGE = "Please wait.";

  public static final int API_PROVIDERS_LIST = 9;
  private static final String API_PROVIDERS_LIST_TITTLE = "Fetching Providers";
  private static final String API_PROVIDERS_LIST_MESSAGE = "Fetching Providers details";

  public static final int API_USER_BALANCE = 10;
  private static final String API_USER_BALANCE_TITTLE = "User";
  private static final String API_USER_BALANCE_MESSAGE = "Fetching User balance..";

  public static final int API_CONTACT_US = 11;
  private static final String API_CONTACT_US_TITTLE = "Sending Data";
  private static final String API_CONTACT_US_MESSAGE = "Please Wait..";

  public static final int API_DISPUTE_HISTORY = 12;
  private static final String API_DISPUTE_HISTORY_TITTLE = "Dispute History";
  private static final String API_DISPUTE_HISTORY_MESSAGE = "Fetching data..";

  public static final int API_VERIFY_MOBILE_NUMBER = 13;
  private static final String API_VERIFY_MOBILE_NUMBER_TITTLE = "Verifying Mobile Number";
  private static final String API_VERIFY_MOBILE_NUMBER_MESSAGE = "Please wait...";

  public static final int API_MONEY_TRANSFER_REQUEST_OTP = 14;
  private static final String API_MONEY_TRANSFER_REQUEST_OTP_TITTLE = "Requesting OTP";
  private static final String API_MONEY_TRANSFER_REQUEST_OTP_MESSAGE = "Please wait...";

  public static final int API_MONEY_TRANSFER_VERIFY_OTP = 15;
  private static final String API_MONEY_TRANSFER_VERIFY_OTP_TITTLE = "OTP";
  private static final String API_MONEY_TRANSFER_VERIFY_OTP_MESSAGE = "Verifying OTP..";

  public static final int API_MONEY_TRANSFER_RECIPIENT_LIST = 16;
  private static final String API_MONEY_TRANSFER_RECIPIENT_LIST_TITTLE = "Recipient";
  private static final String API_MONEY_TRANSFER_RECIPIENT_LIST_MESSAGE = "Fetching Recipient list..";

  public static final int API_MONEY_TRANSFER_ADD_RECIPIENT = 17;
  private static final String API_MONEY_TRANSFER_ADD_RECIPIENT_TITTLE = "Recipient";
  private static final String API_MONEY_TRANSFER_ADD_RECIPIENT_MESSAGE = "Adding Recipient..";

  public static final int API_BANK_DETAILS_LIST = 18;
  private static final String API_BANK_DETAILS_LIST_TITTLE = "Fetching Banks";
  private static final String API_BANK_DETAILS_LIST_MESSAGE = "Fetching Bank details";

  public static final int API_ENROLL_MOBILE_NUMBER = 19;
  private static final String API_ENROLL_MOBILE_NUMBER_TITTLE = "Enrolling Mobile Number";
  private static final String API_ENROLL_MOBILE_NUMBER_MESSAGE = "Please wait...";

  public static final int API_TRANSFER_MONEY = 20;
  private static final String API_TRANSFER_MONEY_TITTLE = "Transferring Money";
  private static final String API_TRANSFER_MONEY_MESSAGE = "Please wait...";

  public static final int API_MONEY_TRANSFER_TRANS_RECIPIENT = 21;
  private static final String API_MONEY_TRANSFER_TRANS_RECIPIENT_TITTLE = "Recipient";
  private static final String API_MONEY_TRANSFER_TRANS_RECIPIENT_MESSAGE = "Adding Recipient..";

  public static final int API_REPORTS = 22;
  private static final String API_REPORTS_TITTLE = "Fetching Reports";
  private static final String API_REPORTS_MESSAGE = "Please wait...";

  public static final String DATA_FAIL_RESPONSE = "fail_response";
  public static final String TIME_OUT_ERROR = "Request Time Out";
  public static final String ERROR_WHILE_CONNECTING_TO_SERVER = "Error while connecting to server";
  public static final String DATA_CHANGE_MESSAGE = "change_message";
  public static final String DEVICE_IS_OFFLINE = "Device is offline";
  public static final String INVALID_AUTH = "Invalid Auth";


  public enum APIEnums {
    API_LOGIN(API_LOGIN_TITLE, API_LOADING_MESSAGE),
    API_REQUEST_OTP(API_REQUEST_OTP_TITTLE, API_REQUEST_OTP_MESSAGE),
    API_VERIFY_OTP(API_VERIFY_OTP_TITTLE, API_VERIFY_OTP_MESSAGE),
    API_CHANGE_PASSWORD(API_CHANGE_PASSWORD_TITTLE, API_CHANGE_PASSWORD_MESSAGE),
    API_USER_INFO(API_USER_INFO_TITTLE, API_USER_INFO_MESSAGE),
    API_USER_BALANCE(API_USER_BALANCE_TITTLE, API_USER_BALANCE_MESSAGE),
    API_MOBILE_RECHARGE(API_MOBILE_RECHARGE_TITTLE, API_MOBILE_RECHARGE_MESSAGE),
    API_RECHARGE_STATUS(API_RECHARGE_STATUS_TITTLE, API_RECHARGE_STATUS_MESSAGE),
    API_RECENT_RECHARGES(API_RECENT_RECHARGES_TITTLE, API_RECENT_RECHARGES_MESSAGE),
    API_PROVIDERS_LIST(API_PROVIDERS_LIST_TITTLE, API_PROVIDERS_LIST_MESSAGE),
    API_CONTACT_US(API_CONTACT_US_TITTLE, API_CONTACT_US_MESSAGE),
    API_DISPUTE_HISTORY(API_DISPUTE_HISTORY_TITTLE, API_DISPUTE_HISTORY_MESSAGE),
    API_VERIFY_MOBILE_NUMBER(API_VERIFY_MOBILE_NUMBER_TITTLE, API_VERIFY_MOBILE_NUMBER_MESSAGE),
    API_MONEY_TRANSFER_REQUEST_OTP(API_MONEY_TRANSFER_REQUEST_OTP_TITTLE, API_MONEY_TRANSFER_REQUEST_OTP_MESSAGE),
    API_MONEY_TRANSFER_VERIFY_OTP(API_MONEY_TRANSFER_VERIFY_OTP_TITTLE, API_MONEY_TRANSFER_VERIFY_OTP_MESSAGE),
    API_MONEY_TRANSFER_RECIPIENT_LIST(API_MONEY_TRANSFER_RECIPIENT_LIST_TITTLE, API_MONEY_TRANSFER_RECIPIENT_LIST_MESSAGE),
    API_MONEY_TRANSFER_ADD_RECIPIENT(API_MONEY_TRANSFER_ADD_RECIPIENT_TITTLE, API_MONEY_TRANSFER_ADD_RECIPIENT_MESSAGE),
    API_BANK_DETAILS_LIST(API_BANK_DETAILS_LIST_TITTLE, API_BANK_DETAILS_LIST_MESSAGE),
    API_ENROLL_MOBILE_NUMBER(API_ENROLL_MOBILE_NUMBER_TITTLE, API_ENROLL_MOBILE_NUMBER_MESSAGE),
    API_TRANSFER_MONEY(API_TRANSFER_MONEY_TITTLE, API_TRANSFER_MONEY_MESSAGE),
    API_MONEY_TRANSFER_TRANS_RECIPIENT(API_MONEY_TRANSFER_TRANS_RECIPIENT_TITTLE, API_MONEY_TRANSFER_TRANS_RECIPIENT_MESSAGE),
    API_REPORTS(API_REPORTS_TITTLE, API_REPORTS_MESSAGE),
    API_DEFAULT(API_PLEASE_WAIT_MESSAGE, API_LOADING_MESSAGE);


    private final String title;
    private final String message;

    APIEnums(String title, String message) {
      this.title = title;
      this.message = message;
    }

    public String getTitle() {
      return title;
    }

    public String getMessage() {
      return message;
    }

  }


}
