package com.shanejansen.libraries;

import android.os.Bundle;
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

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_main;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      addFragment(createOrRetrieveFragment(MainFragment.class), MAIN_CONTAINER, false); // Initial fragment
    }
  }
}
