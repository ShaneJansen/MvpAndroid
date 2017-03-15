package com.shanejansen.mvptest.ui.mainactivity.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;

/**
 * Created by Shane Jansen on 12/13/16.
 */
public class SecondFragment extends AppBaseFragment {
  @Bind(R.id.tv) TextView mTv;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_text;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    mTv.setText(getResources().getString(R.string.fragment_second));
    return v;
  }
}
