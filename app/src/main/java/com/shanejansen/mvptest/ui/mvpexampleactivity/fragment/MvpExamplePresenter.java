package com.shanejansen.mvptest.ui.mvpexampleactivity.fragment;

import com.shanejansen.mvpandroid.mvp.BasePresenter;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExamplePresenter
    extends BasePresenter<MvpExample.ViewForPresenterOps, MvpExample.ViewModelForPresenterOps>
    implements MvpExample.PresenterForViewOps, MvpExample.PresenterForViewModelOps {
  @Override protected void initView() {
    // Called the first time the view is initialized
  }

  @Override protected void updateView() {
    // Called every time the view is updated (orientation changes)
    if (viewModel().getData() != null) onLoadedData();
  }

  @Override protected void loadData() {
    // Called after initView. Initial calls to ViewModel should be made here
  }

  @Override public void onLoadedData() {
    if (viewExists()) {
      view().setDataText(viewModel().getData().getText());
    }
  }

  @Override public void clickedLoadData() {
    viewModel().loadData();
  }

  @Override public void clickSwitchActivities() {
    view().switchActivities();
  }

  @Override public void clickedAddFragment() {
    view().addFragment();
  }
}
