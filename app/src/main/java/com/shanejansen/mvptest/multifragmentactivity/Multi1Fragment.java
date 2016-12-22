package com.shanejansen.mvptest.multifragmentactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.common.AppBaseFragment;

/**
 * Created by Shane Jansen on 12/13/16.
 */
public class Multi1Fragment extends AppBaseFragment {
  @Bind(R.id.tv) TextView mTv;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_text;
  }

  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
    mTv.setText(getResources().getString(R.string.fragment));
  }
}
