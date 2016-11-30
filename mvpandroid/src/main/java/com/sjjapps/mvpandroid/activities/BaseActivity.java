package com.sjjapps.mvpandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.sjjapps.mvpandroid.R;

/**
 * Created by Shane Jansen on 11/16/15.
 *
 * Initial building block for Activities. Sets content view and action bar. Contains tablet checks.
 */
public abstract class BaseActivity extends AppCompatActivity {
  private boolean mIsTablet;

  /**
   * Used to get the layout resource id for this activity.
   *
   * @return The layout resource id.
   */
  protected abstract int getLayoutResourceId();

  /**
   * Used to get the toolbar resource id for this activity.
   *
   * @return The toolbar resource id.
   */
  protected abstract int getToolbarResourceId();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResourceId());

    // Check if this is a tablet
    if (getResources().getBoolean(R.bool.isTablet)) mIsTablet = true;

    // Set the support action bar if a toolbar exists
    Toolbar toolbar = (Toolbar) findViewById(getToolbarResourceId());
    if (toolbar != null) setSupportActionBar(toolbar);
  }

  /**
   * Returns true if this device is a tablet (at least sw820dp).
   *
   * @return True if this device is a tablet
   */
  protected boolean isTablet() {
    return mIsTablet;
  }
}
