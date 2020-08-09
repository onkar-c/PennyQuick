package com.penny.quick.ui.activities.intro_screen;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.penny.quick.R;
import com.penny.quick.ui.activities.dash_board.DashBoardActivity;
import com.penny.quick.ui.activities.login.SignInActivity;
import com.penny.quick.ui.adapters.IntroScreenAdapter;
import com.penny.quick.ui.listeners.IntroScreenListeners;
import java.util.ArrayList;
import java.util.List;

public class IntroScreen extends AppCompatActivity implements IntroScreenListeners {

  private ViewPager vp;
  private List<Integer> screens = new ArrayList<>();
  private ViewPager.OnPageChangeListener viewPagerPageChangeListener =
      new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {}

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {}

        @Override
        public void onPageScrollStateChanged(int arg0) {}
      };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_intro_screen);
    vp = (ViewPager) findViewById(R.id.view_pager);
    screens.add(R.layout.intro_screen1);
    screens.add(R.layout.intro_screen2);
    screens.add(R.layout.intro_screen3);
    IntroScreenAdapter introScreenAdapter = new IntroScreenAdapter(screens, this, this);
    vp.setAdapter(introScreenAdapter);
    vp.addOnPageChangeListener(viewPagerPageChangeListener);
  }

  @Override
  public void onNextClick(int position) {
    if (position == screens.size() - 1) {
      startSignInActivity();
    } else {
      vp.setCurrentItem(position + 1);
    }
  }

  @Override
  public void onSkipClick() {
    startSignInActivity();
  }

  private void startSignInActivity() {
    startActivity(new Intent(this, SignInActivity.class));
    finish();
  }
}
