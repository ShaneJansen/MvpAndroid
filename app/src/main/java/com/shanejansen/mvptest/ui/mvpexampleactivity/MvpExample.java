package com.shanejansen.mvptest.ui.mvpexampleactivity;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public interface MvpExample {
  /**
   * Implemented by the view. These are the operations that are available for the presenter.
   */
  interface ViewForPresenterOps {
  }

  /**
   * Implemented by the presenter. These are the operations that are available for the view.
   */
  interface PresenterForViewOps {
  }

  /**
   * Implemented by the model. These are the operations that are available for the presenter.
   */
  interface ViewModelForPresenterOps {
  }

  /**
   * Implemented by the presenter. These are the operations that are available for the model.
   */
  interface PresenterForViewModelOps {
  }
}
