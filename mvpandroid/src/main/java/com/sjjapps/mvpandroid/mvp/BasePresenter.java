package com.sjjapps.mvpandroid.mvp;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;

/**
 * Created by Shane Jansen on 5/26/16.
 *
 * Building block for presenters in the MVP architecture.
 */
public abstract class BasePresenter<V, M> {
  private boolean mInitialized;
  private WeakReference<V> mView;
  private M mViewModel;

  public BasePresenter() {
    mInitialized = false;
  }

  /**
   * Called only once after the MakeModel is bound. Will not be called again even if the Presenter
   * is restored.
   */
  protected abstract void initView();

  /**
   * Called each time the view is ready to be updated. Should only be called once the View and
   * ViewModel are bound.
   */
  protected abstract void updateView();

  public void viewReady() {
    if (mInitialized) {
      updateView();
    } else {
      initView();
      mInitialized = true;
    }
  }

  /**
   * Returns the Application's Context.
   *
   * @return The Application's Context
   */
  public Context getAppContext() {
    try {
      return ((BaseView) view()).getAppContext();
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Returns the Activity hosting this presenter.
   *
   * @return The Activity hosting this presenter
   */
  public Activity getActivity() {
    try {
      return ((BaseView) view()).getActivity();
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Unbinds the view and view model from this presenter.
   *
   * @param isConfigurationChange True if we are unbinding because of a configuration change
   */
  public void unbind(boolean isConfigurationChange) {
    mView = null;
    ((BaseViewModel) mViewModel).unbindPresenter(isConfigurationChange);
    if (!isConfigurationChange) mViewModel = null;
  }

  /**
   * Binds the view to this presenter.
   *
   * @param view The view to bind
   */
  public void bindView(V view) {
    mView = new WeakReference<>(view);
  }

  protected V view() throws NullPointerException {
    if (mView != null) {
      return mView.get();
    } else {
      throw new NullPointerException("View in unavailable");
    }
  }

  /**
   * Binds the model to this presenter.
   *
   * @param model The model to bind
   */
  public void bindModel(M model) {
    mViewModel = model;
  }

  /**
   * Returns this presenter's view model.
   *
   * @return This presenter's view model
   */
  protected M viewModel() {
    if (mViewModel != null) {
      return mViewModel;
    } else {
      throw new NullPointerException("ViewModel is unavailable");
    }
  }
}
