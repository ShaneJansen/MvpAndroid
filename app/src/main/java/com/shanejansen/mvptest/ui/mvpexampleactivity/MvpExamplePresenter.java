package com.shanejansen.mvptest.ui.mvpexampleactivity;

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
    // Called every time the view is updated (back-stack changes)
  }

  @Override protected void loadData() {
    // Called after initView. Initial calls to ViewModel should be made here
  }
}
