package com.shanejansen.mvptest.ui.mvpexampleactivity;

import com.shanejansen.mvpandroid.mvp.BaseViewModel;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleViewModel extends BaseViewModel<MvpExample.PresenterForViewModelOps>
    implements MvpExample.ViewModelForPresenterOps {
  @Override public void reloadData() {
  }
}
