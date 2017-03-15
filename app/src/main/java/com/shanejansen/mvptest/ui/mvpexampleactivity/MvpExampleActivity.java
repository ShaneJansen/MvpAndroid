package com.shanejansen.mvptest.ui.mvpexampleactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.shanejansen.mvpandroid.activities.MvpActivity;
import com.shanejansen.mvpandroid.mvp.BasePresenter;
import com.shanejansen.mvpandroid.mvp.BaseViewModel;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.mvpexampleactivity.fragment.MvpExampleFragment;
import com.shanejansen.mvptest.ui.secondactivity.SecondActivity;

/**
 * Created by Shane Jansen on 3/15/17.
 */
public class MvpExampleActivity extends MvpActivity<MvpExample.PresenterForViewOps>
    implements MvpExample.ViewForPresenterOps, MvpExampleFragment.MvpExampleFragmentInf {
  public final static int MAIN_CONTAINER = R.id.flFragmentContainer;

  @Override public BaseViewModel getMvpViewModel() {
    return new MvpExampleViewModel();
  }

  @Override public BasePresenter getMvpPresenter() {
    return new MvpExamplePresenter();
  }

  @Override public Activity getActivity() {
    return this;
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_main;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }

  @Override protected int getMainFragmentContainerResourceId() {
    return R.id.flFragmentContainer;
  }

  @Override protected String getActionBarTitle(Fragment fragment) {
    return "MvpExample";
  }

  @Override protected void addInitialFragments() {
    addFragment(new MvpExampleFragment(), MAIN_CONTAINER, false);
  }

  //region MvpExampleFragmentInf
  @Override public void switchActivities() {
    startActivity(new Intent(this, SecondActivity.class));
  }
  //endregion
}
