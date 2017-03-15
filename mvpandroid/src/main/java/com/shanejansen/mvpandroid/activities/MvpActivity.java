package com.shanejansen.mvpandroid.activities;

import android.content.Context;
import android.os.Bundle;
import com.shanejansen.mvpandroid.mvp.BasePresenter;
import com.shanejansen.mvpandroid.mvp.BaseView;
import com.shanejansen.mvpandroid.mvp.BaseViewModel;
import com.shanejansen.mvpandroid.mvp.PresenterMaintainer;

/**
 * Created by Shane Jansen on 3/14/17.
 */
public abstract class MvpActivity<P> extends FragmentActivity implements BaseView {
  private boolean mIsPersisting;
  private P mPresenter;

  @Override public BaseView getMvpView() {
    return this;
  }

  @SuppressWarnings("unchecked") @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      initialMvpBind();
    } else {
      mPresenter = (P) PresenterMaintainer.getInstance().restorePresenter(savedInstanceState);
      if (mPresenter != null) {
        ((BasePresenter) mPresenter).bindView(getMvpView());
      } else {
        initialMvpBind();
      }
    }
    ((BasePresenter) mPresenter).viewReady();
  }

  @SuppressWarnings("unchecked") @Override protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mIsPersisting = true;
    PresenterMaintainer.getInstance().savePresenter((BasePresenter) mPresenter, outState);
  }

  @Override protected void onDestroy() {
    ((BasePresenter) mPresenter).unbind(mIsPersisting);
    super.onDestroy();
  }

  @Override public Context getAppContext() {
    return getApplicationContext();
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
