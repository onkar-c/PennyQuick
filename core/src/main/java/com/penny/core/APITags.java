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


  public static final int API_PULL_PLANTS = 8;
  private static final String API_PULL_PLANTS_TITTLE = "Pull";
  private static final String API_PULL_PLANTS_MESSAGE = "Pulling plant data from Server.";


  public static final int API_USER_STATE = 9;
  private static final String API_USER_STATE_TITTLE = "Changing User State";
  private static final String API_ACTIVATE_USER_MESSAGE = "Changing user state to active..";
  private static final String API_DEACTIVATE_USER_MESSAGE = "Changing user state to de-active..";


  public static final int API_CSV_PLANT = 11;
  private static final String API_CSV_PLANT_TITTLE = "Import plant csv";
//  public static final String IMPORT_PLANT_MSG = "Import plant csv";


  public static final int API_CSV_USERS = 12;
  private static final String API_CSV_USERS_TITTLE = "Import user csv";
//  public static final String API_CSV_USERS_MESSAGE = "Import user csv";


  public static final int API_PUSH_PHOTOS = 13;
  public static final String API_PUSH_PHOTOS_TITTLE = "Push Photos";
  private static final String API_PUSH_PHOTOS_MESSAGE = "Uploading photos to server";


  public static final int API_PULL_PHOTOS = 14;
  private static final String API_PULL_PHOTOS_TITTLE = "Pull Photos";
  private static final String API_PULL_PHOTOS_MESSAGE = "Downloading photos from server.";


  public static final int API_PLANT_LIST = 15;
  private static final String API_PLANT_LIST_TITTLE = "Fetching Plants list";


  public static final int API_AUDIT_TAIL = 16;
  private static final String API_AUDIT_TAIL_TITTLE = "Push Plant Audit Trail";
  private static final String API_AUDIT_TAIL_MESSAGE = "Pushing plant data on Server.";


  public static final int API_RESOLVE_CONFLICT = 17;
  private static final String API_RESOLVE_CONFLICT_TITTLE = "Resolve Conflicts";
//  public static final String MSG_RESOLVING_CONFLICT = "Resolving conflicts";

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
    API_MOBILE_RECHARGE(API_MOBILE_RECHARGE_TITTLE, API_MOBILE_RECHARGE_MESSAGE),
    API_RECHARGE_STATUS(API_RECHARGE_STATUS_TITTLE, API_RECHARGE_STATUS_MESSAGE),
    API_RECENT_RECHARGES(API_RECENT_RECHARGES_TITTLE, API_RECENT_RECHARGES_MESSAGE),
    API_PULL_PLANTS(API_PULL_PLANTS_TITTLE, API_PULL_PLANTS_MESSAGE),
    API_USER_STATE_DE_ACTIVE(API_USER_STATE_TITTLE, API_DEACTIVATE_USER_MESSAGE),
    API_USER_STATE_ACTIVE(API_USER_STATE_TITTLE, API_ACTIVATE_USER_MESSAGE),
    API_CSV_PLANT(API_CSV_PLANT_TITTLE, API_CSV_PLANT_TITTLE),
    API_CSV_USERS(API_CSV_USERS_TITTLE, API_CSV_USERS_TITTLE),
    API_PUSH_PHOTOS(API_PUSH_PHOTOS_TITTLE, API_PUSH_PHOTOS_MESSAGE),
    API_PULL_PHOTOS(API_PULL_PHOTOS_TITTLE, API_PULL_PHOTOS_MESSAGE),
    API_PLANT_LIST(API_PLANT_LIST_TITTLE, API_PLANT_LIST_TITTLE),
    API_AUDIT_TAIL(API_AUDIT_TAIL_TITTLE, API_AUDIT_TAIL_MESSAGE),
    API_RESOLVE_CONFLICT(API_RESOLVE_CONFLICT_TITTLE, API_RESOLVE_CONFLICT_TITTLE),
    API_DEFAULT(API_PLEASE_WAIT_MESSAGE, API_LOADING_MESSAGE);

    private String title, message;

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
