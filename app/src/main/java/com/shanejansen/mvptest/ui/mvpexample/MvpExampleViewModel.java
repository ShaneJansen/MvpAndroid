package com.shanejansen.mvptest.ui.mvpexample;

import com.shanejansen.mvpandroid.mvp.BaseViewModel;
import com.shanejansen.mvptest.data.DataManager;
import com.shanejansen.mvptest.data.models.TestDatum;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleViewModel extends BaseViewModel<MvpExample.PresenterForViewModelOps>
    implements MvpExample.ViewModelForPresenterOps {
  private TestDatum mTestDatum;

  @Override public void reloadData() {
    loadData();
  }

  @Override public void loadData() {
    DataManager.getInstance().test().subscribe(data -> mTes);
  }

  @Override public TestDatum getData() {
    return mTestDatum;
  }
}
