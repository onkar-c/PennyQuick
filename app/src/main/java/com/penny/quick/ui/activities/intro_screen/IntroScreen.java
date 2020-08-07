package com.penny.quick.ui.activities.intro_screen;

import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import com.penny.quick.R;
import com.penny.quick.ui.activities.main.MainActivity;
import com.penny.quick.ui.adapters.IntroScreenAdapter;
import java.util.ArrayList;
import java.util.List;

public class IntroScreen extends AppCompatActivity {

  LinearLayout Layout_bars;
  TextView[] bottomBars;
  Integer[] screens;
  Button Skip, Next;
  ViewPager vp;
  IntroScreenAdapter introScreenAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro_screen);
    vp = (ViewPager) findViewById(R.id.view_pager);
    Layout_bars = (LinearLayout) findViewById(R.id.layoutBars);
    Skip = (Button) findViewById(R.id.skip);
    Next = (Button) findViewById(R.id.next);
    List<Integer> screens = new ArrayList<>();
    screens.add(R.layout.intro_screen1);
    screens.add(R.layout.intro_screen2);
    screens.add(R.layout.intro_screen3);

    this.screens = (Integer[]) screens.toArray(new Integer[0]);
    introScreenAdapter = new IntroScreenAdapter(screens, this);
    vp.setAdapter(introScreenAdapter);

    ColoredBars(0);
  }

  public void next(View v) {
    int i = getItem(+1);
    if (i < screens.length) {
      vp.setCurrentItem(i);
    } else {
      launchMain();
    }
  }

  public void skip(View view) {
    launchMain();
  }

  private void ColoredBars(int thisScreen) {


    bottomBars = new TextView[screens.length];

    Layout_bars.removeAllViews();
    for (int i = 0; i < bottomBars.length; i++) {
      bottomBars[i] = new TextView(this);
      bottomBars[i].setTextSize(100);
      bottomBars[i].setText(Html.fromHtml("¯"));
      Layout_bars.addView(bottomBars[i]);

    }

  }

  private int getItem(int i) {
    return vp.getCurrentItem() + i;
  }

  private void launchMain() {
    startActivity(new Intent(this, MainActivity.class));
    finish();
  }

  ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

    @Override
    public void onPageSelected(int position) {
      ColoredBars(position);
      if (position == screens.length - 1) {
        Next.setText("start");
        Skip.setVisibility(View.GONE);
      } else {
        Next.setText("next");
        Skip.setVisibility(View.VISIBLE);
      }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }
  };

}