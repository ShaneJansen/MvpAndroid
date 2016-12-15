package com.shanejansen.libraries;

import com.shanejansen.libraries.common.AppBaseActivity;

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
}
