package com.shanejansen.mvpandroid.mvp;

import com.shanejansen.mvpandroid.handlers.LoadingHandler;

/**
 * Created by Shane Jansen on 5/24/16.
 *
 * Building block for view models in the MVP architecture.
 */
public abstract class BaseViewModel<P> implements LoadingHandler {
  private P mPresenter;

  public BaseViewModel() {
  }

  /**
   * Binds the presenter to this view model.
   *
   * @param presenter The presenter to bind
   */
  public void bindPresenter(P presenter) {
    mPresenter = presenter;
  }

  /**
   * Unbints the presenter from this view model.
   *
   * @param isConfigurationChange True if we are unbinding because of a configuration change
   */
  public void unbindPresenter(boolean isConfigurationChange) {
    if (!isConfigurationChange) mPresenter = null;
  }

  /**
   * Returns this view model's presenter.
   *
   * @return This view models' presenter
   */
  public P presenter() {
    if (mPresenter != null) {
      return mPresenter;
    } else {
      throw new NullPointerException("Presenter is unavailable");
    }
  }
}
