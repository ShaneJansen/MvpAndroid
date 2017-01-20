package com.shanejansen.mvptest.ui.common;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.shanejansen.mvpandroid.activities.BaseActivity;

/**
 * Created by Shane Jansen on 12/6/16.
 */
public abstract class AppBaseActivity extends BaseActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
