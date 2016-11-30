package com.sjjapps.mvpandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sjjapps.mvpandroid.mvp.BasePresenter;
import com.sjjapps.mvpandroid.mvp.BaseView;
import com.sjjapps.mvpandroid.mvp.BaseViewModel;
import com.sjjapps.mvpandroid.mvp.PresenterMaintainer;

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
   * Used the get the default MVP model. This is the model that will be used if one is not
   * specified during Fragment creation.
   *
   * @return The default MVP model
   */
  protected abstract M getDefaultMvpModel();

  /**
   * Used to get the default MVP view. This is the view that will be used if one is not specified
   * during Fragment creation.
   *
   * @return The default MVP view
   */
  protected abstract V getMvpView();

  /**
   * Used to get the default MVP presenter. This is the presenter that will be used if one is not
   * specified during Fragment creation.
   *
   * @return The default MVP presenter
   */
  protected abstract P getDefaultMvpPresenter();

  @SuppressWarnings("unchecked") @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      /*mPresenter = getMvpPresenter();
      M model = getMvpModel();
      mPresenter.bindView(getMvpView());
      if (mTestModel == null) {
        mPresenter.bindModel(model);
      } else {
        mPresenter.bindModel(mTestModel);
      }
      model.bindPresenter(mPresenter);*/

      if (mPresenter == null) mPresenter = getDefaultMvpPresenter();
      if (mViewModel == null) mViewModel = getDefaultMvpModel();
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
   * @param customPresenter The custom presenter object
   */
  public void setCustomPresenter(P customPresenter) {
    mPresenter = customPresenter;
  }

  /**
   * Sets a custom view model overriding the default.
   *
   * @param customModel The custom view model object
   */
  public void setCustomModel(M customModel) {
    mViewModel = customModel;
  }
}
