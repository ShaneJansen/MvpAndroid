package com.shanejansen.mvptest.ui.common;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import com.shanejansen.mvpandroid.fragments.MvpFragment;

/**
 * Created by Shane Jansen on 12/4/16.
 */
public abstract class AppMvpFragment<P> extends MvpFragment<P> {
  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
    ButterKnife.bind(this, v);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
