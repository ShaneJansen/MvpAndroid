package com.shanejansen.mvpandroid.mvp;

/**
 * Created by Shane Jansen on 5/24/16.
 *
 * Building block for view models in the MVP architecture.
 */
public abstract class BaseViewModel<P> {
  private P mPresenter;

  public BaseViewModel() {
  }

  /**
   * Reloads this view's data.
   */
  public abstract void reloadData();

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
   * @param isPersisting True if we are persisting the MVP
   */
  public void unbind(boolean isPersisting) {
    if (!isPersisting) mPresenter = null;
  }

  /**
   * Returns true if this ViewModel is bound to a Presenter.
   *
   * @return true if this ViewModel is bound to a Presenter
   */
  public boolean presenterExists() {
    return mPresenter != null;
  }

  /**
   * Returns this view model's presenter.
   *
   * @return This view models' presenter
   */
  protected P presenter() {
    if (mPresenter != null) {
      return mPresenter;
    } else {
      throw new NullPointerException("Presenter is unavailable");
    }
  }
}
