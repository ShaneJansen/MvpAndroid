package com.shanejansen.mvpandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shanejansen.mvpandroid.mvp.BasePresenter;
import com.shanejansen.mvpandroid.mvp.BaseView;
import com.shanejansen.mvpandroid.mvp.BaseViewModel;
import com.shanejansen.mvpandroid.mvp.PresenterMaintainer;

/**
 * Created by Shane Jansen on 5/27/16.
 *
 * Building block for Fragments using the MVP architecture. All binding for the MVP architecture is
 * done here.
 */
public abstract class MvpFragment<P> extends BaseFragment implements BaseView {
  private boolean mIsPersisting;
  private P mPresenter;

  /**
   * Used to get the MVP view.
   *
   * @return The MVP view
   */
  protected abstract <V extends BaseView> V getMvpView();

  @SuppressWarnings("unchecked") @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      if (mPresenter == null) {
        throw new RuntimeException("Presenter must be set first using bindPresenter(..)");
      }
      if (!(mPresenter instanceof BasePresenter)) {
        throw new RuntimeException("Presenter must be an instanceof BasePresenter");
      }
      if (!((BasePresenter) mPresenter).viewModelExists()) {
        throw new RuntimeException("ViewModel must be set first using set bindViewModel(..)");
      }
    } else {
      mPresenter = (P) PresenterMaintainer.getInstance().restorePresenter(savedInstanceState);
      ((BasePresenter) mPresenter).bindView(getMvpView());
    }
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    ((BasePresenter) mPresenter).viewReady();
    return v;
  }

  @SuppressWarnings("unchecked") @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mIsPersisting = true;
    PresenterMaintainer.getInstance().savePresenter(mPresenter, outState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    ((BasePresenter) mPresenter).unbind(mIsPersisting);
  }

  @Override public Context getAppContext() {
    return super.getAppContext();
  }

  /**
   * Sets a custom presenter overriding the default.
   *
   * @param presenter The custom presenter object
   */
  @SuppressWarnings("unchecked") public void bindPresenter(P presenter) {
    mPresenter = presenter;
    ((BasePresenter) mPresenter).bindView(getMvpView());
  }

  /**
   * Sets a custom view model overriding the default.
   *
   * @param viewModel The custom view model object
   */
  @SuppressWarnings("unchecked") public <M> void bindViewModel(M viewModel) {
    if (mPresenter == null) {
      throw new RuntimeException("Presenter must be set first using bindPresenter(..)");
    }
    ((BasePresenter) mPresenter).bindViewModel(viewModel);
    ((BaseViewModel) viewModel).bindPresenter(mPresenter);
  }

  /**
   * Returns the presenter for this view.
   *
   * @return The presenter
   */
  protected P presenter() {
    return mPresenter;
  }
}
