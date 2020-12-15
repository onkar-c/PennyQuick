package com.penny.core.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.penny.database.ProjectConstants;
import com.penny.database.utils.DateUtils;
import java.lang.reflect.Type;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Long> {

  @Override
  public Long deserialize(JsonElement date, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    String dateStr = date.getAsString();
      Date convertedDate = DateUtils.convertToDate(dateStr, ProjectConstants.SERVER_DATE_TIME_FORMAT);
      if (convertedDate != null) {
        return convertedDate.getTime();
      } else {
        return Long.parseLong(dateStr);
      }

  }
}
