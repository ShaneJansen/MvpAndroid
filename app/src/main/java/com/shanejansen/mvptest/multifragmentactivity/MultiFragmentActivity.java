package com.shanejansen.mvptest.multifragmentactivity;

import android.support.v4.app.Fragment;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.common.AppFragmentActivity;

/**
 * Created by Shane Jansen on 12/22/16.
 */
public class MultiFragmentActivity extends AppFragmentActivity {
  public final static int MAIN_CONTAINER = R.id.flFragmentContainer1;
  public final static int SECOND_CONTAINER = R.id.flFragmentContainer2;

  @Override protected int getMainFragmentContainerResourceId() {
    return MAIN_CONTAINER;
  }

  @Override protected String getActionBarTitle(Fragment fragment) {
    return "MultiFragmentActivity";
  }

  @Override protected void addInitialFragments() {
    addFragment(new Multi1Fragment(), MAIN_CONTAINER, false);
    addFragment(new Multi2Fragment(), SECOND_CONTAINER, false);
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_multi_fragment;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }
}
