package com.shanejansen.libraries;

import android.support.v4.app.Fragment;
import com.shanejansen.libraries.common.AppFragmentActivity;

/**
 * Created by Shane Jansen on 11/29/16.
 */
public class MainActivity extends AppFragmentActivity {
  public final static int MAIN_CONTAINER = R.id.flFragmentContainer;

  @Override protected int getMainFragmentContainerResourceId() {
    return R.id.flFragmentContainer;
  }

  @Override protected String getActionBarTitle(Fragment fragment) {
    if (fragment instanceof MainFragment) {
      return "MainFragment";
    } else if (fragment instanceof SecondFragment) return "SecondFragment";
    return "";
  }

  @Override protected void addInitialFragments() {
    addFragment(new MainFragment(), MAIN_CONTAINER, false);
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_main;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }
}
