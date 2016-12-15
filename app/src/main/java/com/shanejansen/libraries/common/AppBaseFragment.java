package com.shanejansen.libraries.common;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import com.shanejansen.mvpandroid.fragments.BaseFragment;

/**
 * Created by Shane Jansen on 12/5/16.
 */
public abstract class AppBaseFragment extends BaseFragment {
  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
    ButterKnife.bind(this, v);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
