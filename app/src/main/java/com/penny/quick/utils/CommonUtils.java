package com.penny.quick.utils;

import android.content.Context;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.penny.quick.R;
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

  public static void getImage(Context context, String imgUrl, ImageView imageView,
      int drawableId) {
    Glide.with(context)
        .load(imgUrl != null ? imgUrl : "")
        .placeholder(drawableId == 0 ? R.drawable.hamburger_icon : drawableId)
        .into(imageView)
        .onLoadFailed(
            ContextCompat.getDrawable(context, R.drawable.hamburger_icon));
  }
}
