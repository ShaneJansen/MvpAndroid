package com.shanejansen.mvptest.mainactivity;

import android.support.v4.app.Fragment;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.common.AppFragmentActivity;
import com.shanejansen.mvptest.mvpexample.MvpExampleFragment;

/**
 * Created by Shane Jansen on 11/29/16.
 */
public class MainActivity extends AppFragmentActivity {
  public final static int MAIN_CONTAINER = R.id.flFragmentContainer;

  @Override protected int getMainFragmentContainerResourceId() {
    return MAIN_CONTAINER;
  }

  @Override protected String getActionBarTitle(Fragment fragment) {
    if (fragment instanceof FirstFragment) {
      return "FirstFragment";
    } else if (fragment instanceof SecondFragment) {
      return "SecondFragment";
    } else if (fragment instanceof MvpExampleFragment) return "MvpExampleFragment";
    return "";
  }

  @Override protected void addInitialFragments() {
    addFragment(new FirstFragment(), MAIN_CONTAINER, false);
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_main;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }
}
