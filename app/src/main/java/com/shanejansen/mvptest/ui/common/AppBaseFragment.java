package com.shanejansen.mvptest.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.shanejansen.mvpandroid.fragments.BaseFragment;

/**
 * Created by Shane Jansen on 12/5/16.
 */
public abstract class AppBaseFragment extends BaseFragment {
  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, v);
    return v;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
