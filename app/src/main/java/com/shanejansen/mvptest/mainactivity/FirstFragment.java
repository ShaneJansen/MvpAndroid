package com.shanejansen.mvptest.mainactivity;

import android.content.Intent;
import butterknife.OnClick;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.common.AppBaseFragment;
import com.shanejansen.mvptest.multifragmentactivity.MultiFragmentActivity;
import com.shanejansen.mvptest.mvpexample.MvpExampleFragment;
import com.shanejansen.mvptest.mvpexample.MvpExamplePresenter;
import com.shanejansen.mvptest.mvpexample.MvpExampleViewModel;
import com.shanejansen.mvptest.secondactivity.SecondActivity;

/**
 * Created by Shane Jansen on 12/13/16.
 */
public class FirstFragment extends AppBaseFragment {
  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_main;
  }

  @OnClick(R.id.btnAddFragment) void btnAddFragmentClicked() {
    getTransactionHandler().addFragment(new SecondFragment(), MainActivity.MAIN_CONTAINER, true);
  }

  @OnClick(R.id.btnAddMvpFragment) void btnAddMvpFragmentClicked() {
    MvpExampleFragment mvpExampleFragment = new MvpExampleFragment();
    mvpExampleFragment.setPresenter(new MvpExamplePresenter());
    mvpExampleFragment.setViewModel(new MvpExampleViewModel());
    getTransactionHandler().addFragment(mvpExampleFragment, MainActivity.MAIN_CONTAINER, true);
  }

  @OnClick(R.id.btnSwitchActivities) void btnSwitchActivitiesClicked() {
    startActivity(new Intent(getAppContext(), SecondActivity.class));
  }

  @OnClick(R.id.btnMultiFragmentActivity) void btnMultiFragmentActivityClicked() {
    startActivity(new Intent(getAppContext(), MultiFragmentActivity.class));
  }
}
