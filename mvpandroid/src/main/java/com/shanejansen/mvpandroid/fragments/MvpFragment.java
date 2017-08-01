package com.shanejansen.mvpandroid.fragments;

import android.content.Context;
import android.os.Bundle;
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
  private boolean mShouldUpdateView, mIsPersisting;
  private P mPresenter;

  @Override public MvpFragment getMvpView() {
    return this;
  }

  @SuppressWarnings("unchecked") @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      initialMvpBind();
    } else {
      mPresenter = (P) PresenterMaintainer.getInstance().restorePresenter(savedInstanceState);
      /*
        The PresenterMaintainer could have expelled the Presenter from the cache or Android could
        have killed the Application process destroying the PresenterMaintainer.
       */
      if (mPresenter != null) {
        ((BasePresenter) mPresenter).bindView(getMvpView());
      } else {
        initialMvpBind();
      }
    }

    // View should be updated after onCreate (initial creation or orientation not back-stack)
    mShouldUpdateView = true;
  }

  @Override public void onStart() {
    super.onStart();
    if (mShouldUpdateView) {
      ((BasePresenter) mPresenter).viewReady();
      mShouldUpdateView = false;
    }
  }

  @SuppressWarnings("unchecked") @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mIsPersisting = true;
    PresenterMaintainer.getInstance().savePresenter((BasePresenter) mPresenter, outState);
  }

  @Override public void onDestroy() {
    ((BasePresenter) mPresenter).unbind(mIsPersisting);
    super.onDestroy();
  }

  @Override public Context getAppContext() {
    return super.getAppContext();
  }

  @SuppressWarnings("unchecked") private void initialMvpBind() {
    mPresenter = (P) getMvpPresenter();
    BaseViewModel viewModel = getMvpViewModel();
    ((BasePresenter) mPresenter).bindView(getMvpView());
    ((BasePresenter) mPresenter).bindViewModel(viewModel);
    viewModel.bindPresenter(mPresenter);
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
