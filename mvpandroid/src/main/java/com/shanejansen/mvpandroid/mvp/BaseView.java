package com.shanejansen.mvpandroid.mvp;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Shane Jansen on 5/26/16.
 *
 * Building block for views in the MVP architecture.
 */
public interface BaseView<P extends BasePresenter> {

  /**
   * Used to get the MVP view.
   *
   * @return The MVP view
   */
  <V extends BaseView> V getMvpView();

  P getMvpPresenter();

  <M extends BaseViewModel> M getMvpViewModel();

  /**
   * Returns the Application's Context.
   *
   * @return The Application's Context
   */
  Context getAppContext();

  /**
   * Returns the Activity hosting this view.
   *
   * @return The Activity hosting this view
   */
  Activity getActivity();
}
