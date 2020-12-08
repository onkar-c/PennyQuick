package com.penny.quick.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.penny.quick.R;
import com.penny.quick.ui.listeners.IntroScreenListeners;
import java.util.List;

public class IntroScreenAdapter extends PagerAdapter {

  private final List<Integer> screens;
  private final Context mContext;
  private final IntroScreenListeners introScreenListeners;

  public IntroScreenAdapter(
      List<Integer> screens, Context mContext, IntroScreenListeners introScreenListeners) {
    this.screens = screens;
    this.mContext = mContext;
    this.introScreenListeners = introScreenListeners;
  }

  @NonNull
  @Override
  public Object instantiateItem(@NonNull ViewGroup container, int position) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(screens.get(position), container, false);
    (view.findViewById(R.id.bt_continue))
        .setOnClickListener(view1 -> introScreenListeners.onNextClick(position));
    (view.findViewById(R.id.skip))
        .setOnClickListener(view1 -> introScreenListeners.onSkipClick());
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
