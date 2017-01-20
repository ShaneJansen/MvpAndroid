package com.shanejansen.mvptest.ui.mvpexample;

import com.shanejansen.mvptest.data.models.TestDatum;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public interface MvpExample {
  /**
   * Implemented by the view. These are the operations that are available for the presenter.
   */
  interface ViewForPresenterOps {
    void setDataText(String data);
  }

  /**
   * Implemented by the presenter. These are the operations that are available for the view.
   */
  interface PresenterForViewOps {
    void clickedLoadData();
  }

  /**
   * Implemented by the model. These are the operations that are available for the presenter.
   */
  interface ViewModelForPresenterOps {
    void loadData();

    TestDatum getData();
  }

  /**
   * Implemented by the presenter. These are the operations that are available for the model.
   */
  interface PresenterForViewModelOps {
    void onLoadedData();
  }
}