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
    DataManager.getInstance().getTestDatum(new DataManager.Response<TestDatum>() {
      @Override public void success(TestDatum response, int httpCode) {
        mTestDatum = response;
        if (presenterExists()) presenter().onLoadedData();
      }

      @Override public void failure(String response, int httpCode) {
      }
    });
  }

  @Override public TestDatum getData() {
    return mTestDatum;
  }
}
