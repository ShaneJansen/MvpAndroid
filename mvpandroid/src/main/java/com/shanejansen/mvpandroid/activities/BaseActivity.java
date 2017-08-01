package com.shanejansen.mvpandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.shanejansen.mvpandroid.R;

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
   * Used to get the toolbar resource id for this activity.  This toolbar will be set as the
   * application's support action bar.  If you want to access the toolbar/actionbar, the
   * getSupportActionBar() method should be used.
   *
   * @return The toolbar resource id.
   */
  protected abstract int getToolbarResourceId();

  @Override protected void onCreate(Bundle savedInstanceState) {
    setContentView(getLayoutResourceId());

    // Set the support action bar if a toolbar exists
    Toolbar toolbar = (Toolbar) findViewById(getToolbarResourceId());
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }

    /*
      The reason we are calling super here is because super is where the Fragments are added to the
      Activity. If we are in the process of re-creating this Activity (like after a rotation
      change), the onAttachFragment method needs to be called after the ActionBar has been set so
      the ActionBar title is set correctly. This Fragment logic exists in BaseActivity because the
      setActionBarNavigation (see onStart()) method also applies to a standard Activity.
     */
    super.onCreate(savedInstanceState);

    // Check if this is a tablet
    if (getResources().getBoolean(R.bool.isTablet)) mIsTablet = true;
  }

  @Override protected void onStart() {
    super.onStart();
    // The ActionBar navigation needs to be set AFTER the Fragments and views have been loaded
    setActionBarNavigation();
  }

  @Override public boolean onSupportNavigateUp() {
    finish();
    return true;
  }

  /**
   * Returns true if this device is a tablet (at least sw820dp).
   *
   * @return True if this device is a tablet
   */
  protected boolean isTablet() {
    return mIsTablet;
  }

  /**
   * Sets the action bar navigation icons based on the number of Activities or Fragments in the
   * back-stack.
   */
  protected void setActionBarNavigation() {
    if (getSupportActionBar() != null) {
      int numBack = getSupportFragmentManager().getBackStackEntryCount();
      Intent upIntent = NavUtils.getParentActivityIntent(this);
      if (numBack != 0 || upIntent != null) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
      } else {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
      }
    }
  }
}
