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
public abstract class MvpFragment<M extends BaseViewModel, V extends BaseView, P extends BasePresenter>
    extends BaseFragment implements BaseView {
  private P mPresenter;
  private M mViewModel;

  /**
   * Used to get the MVP view.
   *
   * @return The MVP view
   */
  protected abstract V getMvpView();

  @SuppressWarnings("unchecked") @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      if (mPresenter == null) {
        throw new RuntimeException("Presenter must be set first using setPresenter(..)");
      }
      if (mViewModel == null) {
        throw new RuntimeException("ViewModel must be set first using set setViewModel(..)");
      }
      mPresenter.bindView(getMvpView());
      mPresenter.bindModel(mViewModel);
      mViewModel.bindPresenter(mPresenter);
    } else {
      mPresenter = PresenterMaintainer.getInstance().restorePresenter(savedInstanceState);
      mPresenter.bindView(getMvpView());
    }
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    mPresenter.viewReady();
    return v;
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    PresenterMaintainer.getInstance().savePresenter(mPresenter, outState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mPresenter.unbind(getActivity().isChangingConfigurations());
  }

  @Override public Context getAppContext() {
    return super.getAppContext();
  }

  /**
   * Returns the presenter for this view.
   *
   * @return The presenter
   */
  public P presenter() {
    return mPresenter;
  }

  /**
   * Returns the viewModel for this view.
   *
   * @return The viewModel
   */
  public M viewModel() {
    return mViewModel;
  }

  /**
   * Sets a custom presenter overriding the default.
   *
   * @param presenter The custom presenter object
   */
  public void setPresenter(P presenter) {
    mPresenter = presenter;
  }

  /**
   * Sets a custom view model overriding the default.
   *
   * @param viewModel The custom view model object
   */
  public void setViewModel(M viewModel) {
    mViewModel = viewModel;
  }
}
