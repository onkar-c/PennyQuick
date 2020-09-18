package com.penny.quick.utils;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;

public class CommonUtils {

  public static String loadData(String inFile, Context context) {
    String tContents = "";

    try {
      InputStream stream = context.getAssets().open(inFile);
      int size = stream.available();
      byte[] buffer = new byte[size];
      stream.read(buffer);
      stream.close();
      tContents = new String(buffer);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tContents;
  }
}
