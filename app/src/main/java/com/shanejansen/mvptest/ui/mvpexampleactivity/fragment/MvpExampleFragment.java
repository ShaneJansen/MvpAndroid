package com.shanejansen.mvptest.ui.mvpexampleactivity.fragment;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.shanejansen.mvpandroid.mvp.BasePresenter;
import com.shanejansen.mvpandroid.mvp.BaseViewModel;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.common.AppMvpFragment;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleFragment extends AppMvpFragment<MvpExample.PresenterForViewOps>
    implements MvpExample.ViewForPresenterOps {
  @Bind(R.id.tvData) TextView mTvData;
  private MvpExampleFragmentInf mMvpExampleFragmentInf;

  @Override public BasePresenter getMvpPresenter() {
    return new MvpExamplePresenter();
  }

  @Override public BaseViewModel getMvpViewModel() {
    return new MvpExampleViewModel();
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_mvp_example;
  }

  @Override public void setDataText(String data) {
    mTvData.setText(data);
  }

  @Override public void switchActivities() {
    mMvpExampleFragmentInf.switchActivities();
  }

  @OnClick(R.id.btnLoadData) void btnLoadDataClicked() {
    presenter().clickedLoadData();
  }

  @OnClick(R.id.btnSwitchActivities) void btnSwitchActivitiesClicked() {
    presenter().clickSwitchActivities();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mMvpExampleFragmentInf = getParentInterface(MvpExampleFragmentInf.class);
  }

  public interface MvpExampleFragmentInf {
    /*
      This is where you would define callback from this Fragment to the hosting Activity or
      Fragment. The hosting Activity or Fragment would define some specific operations for this
      Fragment in order to make it re-usable. For example, if this Fragment was a list, we would
      have a method here to define what happens when a list item is clicked.
     */
    void switchActivities();
  }
}
