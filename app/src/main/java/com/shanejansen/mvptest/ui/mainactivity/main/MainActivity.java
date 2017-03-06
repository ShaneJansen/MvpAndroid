package com.shanejansen.mvptest.ui.mainactivity.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.common.AppFragmentActivity;
import com.shanejansen.mvptest.ui.mainactivity.mvpexample.MvpExampleFragment;
import com.shanejansen.mvptest.ui.secondactivity.SecondActivity;

/**
 * Created by Shane Jansen on 11/29/16.
 */
public class MainActivity extends AppFragmentActivity
    implements MvpExampleFragment.MvpExampleFragmentInf {
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

  //region MvpExampleFragmentInf
  @Override public void switchActivities() {
    startActivity(new Intent(this, SecondActivity.class));
  }
  //endregion
}
