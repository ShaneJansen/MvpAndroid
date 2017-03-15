package com.shanejansen.mvptest.ui.mainactivity.main;

import android.content.Intent;
import butterknife.OnClick;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;
import com.shanejansen.mvptest.ui.mvpexampleactivity.MvpExampleActivity;
import com.shanejansen.mvptest.ui.mvpexampleactivity.fragment.MvpExampleFragment;
import com.shanejansen.mvptest.ui.mainactivity.recycler.SectionedRecyclerFragment;
import com.shanejansen.mvptest.ui.mainactivity.recycler.SimpleRecyclerFragment;
import com.shanejansen.mvptest.ui.mainactivity.rxjava.RxJavaFragment;
import com.shanejansen.mvptest.ui.multifragmentactivity.MultiFragmentActivity;
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

  @OnClick(R.id.btnAddMvpFragment) void btnAddMvpActivityClicked() {
    startActivity(new Intent(getAppContext(), MvpExampleActivity.class));
  }

  @OnClick(R.id.btnSwitchActivities) void btnSwitchActivitiesClicked() {
    startActivity(new Intent(getAppContext(), SecondActivity.class));
  }

  @OnClick(R.id.btnMultiFragmentActivity) void btnMultiFragmentActivityClicked() {
    startActivity(new Intent(getAppContext(), MultiFragmentActivity.class));
  }

  @OnClick(R.id.btnRxJava) void btnRxJavaClicked() {
    getTransactionHandler().addFragment(new RxJavaFragment(), MainActivity.MAIN_CONTAINER, true);
  }

  @OnClick(R.id.btnSimpleRecyclerExample) void btnSimpleRecyclerExampleClicked() {
    getTransactionHandler().addFragment(new SimpleRecyclerFragment(), MainActivity.MAIN_CONTAINER,
        true);
  }

  @OnClick(R.id.btnSectionedRecyclerExample) void btnSectionedRecyclerExampleClicked() {
    getTransactionHandler().addFragment(new SectionedRecyclerFragment(),
        MainActivity.MAIN_CONTAINER, true);
  }
}
