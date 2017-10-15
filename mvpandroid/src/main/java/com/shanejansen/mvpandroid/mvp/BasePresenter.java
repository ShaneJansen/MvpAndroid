package com.shanejansen.mvpandroid.mvp;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;

/**
 * Created by Shane Jansen on 5/26/16.
 *
 * Building block for presenters in the MVP architecture.
 */
public abstract class BasePresenter<V, M> {
  private boolean mInitialized, mShouldLoadInitially;
  private WeakReference<V> mView;
  private M mViewModel;

  public BasePresenter() {
    mInitialized = false;
    mShouldLoadInitially = true;
  }

  /**
   * Called the first time this Presenter is initialized. Will not be called again even if the
   * Presenter is restored.
   */
  protected abstract void init();

  /**
   * Called every time the view is updated (including back-stack changes). Updates/restores to the
   * view should be done here.
   */
  protected abstract void updateView();

  /**
   * Called after init unless shouldLoadInitially is set to false. All data calls to the
   * ViewModel should be placed here.
   */
  protected abstract void loadData();

  public void viewReady() {
    if (mInitialized) {
      updateView();
    } else {
      init();
      updateView();
      if (mShouldLoadInitially) loadData();
      mInitialized = true;
    }
  }

  /**
   * Called whether nested Fragments exist or not. Called when all Fragments within this
   * Activity/Fragment are ready to be called upon.
   */
  public void nestedFragmentsReady() {
  }

  /**
   * Binds the view to this presenter.
   *
   * @param view The view to bind
   */
  public void bindView(V view) {
    mView = new WeakReference<>(view);
  }

  /**
   * Binds the model to this presenter.
   *
   * @param model The model to bind
   */
  public void bindViewModel(M model) {
    mViewModel = model;
  }

  /**
   * Unbinds the view from this presenter.
   */
  public void unbindView() {
    mView = null;
  }

  /**
   * Unbinds the view model from this presenter.
   */
  public void unbindViewModel() {
    ((BaseViewModel) mViewModel).unbindPresenter();
    mViewModel = null;
  }

  /**
   * Returns true if this Presenter is bound to a View.
   *
   * @return true if this Presenter is bound to a View
   */
  public boolean viewExists() {
    return mView != null;
  }

  /**
   * Returns true if this Presenter is bound to a ViewModel.
   *
   * @return true if this Presenter is bound to a ViewModel
   */
  public boolean viewModelExists() {
    return mViewModel != null;
  }

  /**
   * Set to false if the view should not load any data initially. Data can always be loaded later
   * by calling the loadData method. Default is true.
   *
   * @param shouldLoadInitially false if the view should not load any data initially
   */
  public void setShouldLoadInitially(boolean shouldLoadInitially) {
    mShouldLoadInitially = shouldLoadInitially;
  }

  protected V view() {
    if (mView != null) {
      return mView.get();
    } else {
      throw new NullPointerException("View in unavailable");
    }
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

  /**
   * Returns the Application's Context.
   *
   * @return The Application's Context
   */
  protected Context getAppContext() {
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
  protected Activity getActivity() {
    try {
      return ((BaseView) view()).getActivity();
    } catch (NullPointerException e) {
      return null;
    }
  }
}
