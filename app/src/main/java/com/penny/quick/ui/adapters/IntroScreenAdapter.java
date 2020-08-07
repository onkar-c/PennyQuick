package com.penny.quick.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class IntroScreenAdapter extends PagerAdapter {

  List<Integer> screens;
  Context mContext;

  public IntroScreenAdapter(List<Integer> screens, Context mContext) {
    this.screens = screens;
    this.mContext = mContext;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = (ViewGroup) inflater.inflate(screens.get(position), container, false);
    container.addView(view);
    return view;
  }

  @Override
  public int getCount() {
    return screens.size();
  }

  @Override
  public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
    View v = (View) object;
    container.removeView(v);
  }

  @Override
  public boolean isViewFromObject(@NonNull View v, @NonNull Object object) {
    return v == object;
  }
}
