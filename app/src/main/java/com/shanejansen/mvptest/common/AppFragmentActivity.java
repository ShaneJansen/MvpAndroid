package com.shanejansen.mvptest.common;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.shanejansen.mvpandroid.activities.FragmentActivity;

/**
 * Created by Shane Jansen on 12/4/16.
 */
public abstract class AppFragmentActivity extends FragmentActivity {
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
