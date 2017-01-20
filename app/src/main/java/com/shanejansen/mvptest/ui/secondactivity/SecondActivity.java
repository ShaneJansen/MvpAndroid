package com.shanejansen.mvptest.ui.secondactivity;

import android.os.Bundle;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.ui.common.AppBaseActivity;

/**
 * Created by Shane Jansen on 12/13/16.
 */
public class SecondActivity extends AppBaseActivity {
  @Override protected int getLayoutResourceId() {
    return R.layout.activity_second;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    assert getSupportActionBar() != null;
    getSupportActionBar().setTitle("SecondActivity");
  }
}
