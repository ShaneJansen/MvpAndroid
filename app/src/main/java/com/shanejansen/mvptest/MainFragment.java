package com.shanejansen.mvptest;

import android.content.Intent;
import butterknife.OnClick;
import com.shanejansen.mvptest.common.AppBaseFragment;

/**
 * Created by Shane Jansen on 12/13/16.
 */
public class MainFragment extends AppBaseFragment {
  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_main;
  }

  @OnClick(R.id.btnAddFragment) void btnAddFragmentClicked() {
    getTransactionHandler().addFragment(new SecondFragment(), MainActivity.MAIN_CONTAINER, true);
  }

  @OnClick(R.id.btnSwitchActivities) void btnSwitchActivitiesClicked() {
    startActivity(new Intent(getAppContext(), SecondActivity.class));
  }
}
