package com.shanejansen.mvptest.mvpexample;

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

    String getData();
  }

  /**
   * Implemented by the presenter. These are the operations that are available for the model.
   */
  interface PresenterForViewModelOps {
    void onLoadedData();
  }
}
