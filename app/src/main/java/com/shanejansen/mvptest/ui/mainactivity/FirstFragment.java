package com.shanejansen.mvptest.ui.mainactivity;

import android.content.Intent;
import butterknife.OnClick;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;
import com.shanejansen.mvptest.ui.multifragmentactivity.MultiFragmentActivity;
import com.shanejansen.mvptest.ui.mvpexample.MvpExampleFragment;
import com.shanejansen.mvptest.ui.secondactivity.SecondActivity;

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
    getTransactionHandler().addFragment(new MvpExampleFragment(), MainActivity.MAIN_CONTAINER,
        true);
  }

  @OnClick(R.id.btnSwitchActivities) void btnSwitchActivitiesClicked() {
    startActivity(new Intent(getAppContext(), SecondActivity.class));
  }

  @OnClick(R.id.btnMultiFragmentActivity) void btnMultiFragmentActivityClicked() {
    startActivity(new Intent(getAppContext(), MultiFragmentActivity.class));
  }

  @OnClick(R.id.btnSimpleRecyclerExample) void btnSimpleRecyclerExampleClicked() {
    getTransactionHandler().addFragment(new SimpleRecyclerFragment(), MainActivity.MAIN_CONTAINER,
        true);
  }

  @OnClick(R.id.btnSectionedRecyclerExample) void btnSectionedRecyclerExampleClicked() {
    /*getTransactionHandler().addFragment(new SectionedRecyclerFragment(),
        MainActivity.MAIN_CONTAINER, true);*/
  }
}
