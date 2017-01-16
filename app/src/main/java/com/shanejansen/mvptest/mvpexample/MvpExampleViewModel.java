package com.shanejansen.mvptest.mvpexample;

import com.shanejansen.mvpandroid.mvp.BaseViewModel;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleViewModel extends BaseViewModel<MvpExample.PresenterForViewModelOps>
    implements MvpExample.ViewModelForPresenterOps {
  private String mData;

  @Override public void reloadData() {
    loadData();
  }

  @Override public void loadData() {
    // Mock data load
    mData = "This is some loaded data";
    presenter().onLoadedData();
  }

  @Override public String getData() {
    return mData;
  }
}
