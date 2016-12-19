package com.shanejansen.mvpandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import com.shanejansen.mvpandroid.handlers.TransactionHandler;

/**
 * Created by Shane Jansen on 5/31/16.
 *
 * Building block for Activities which handle Fragment. Sets actionbar. Contains methods for
 * Fragment transactions, and setting action bar status. Handles back-stack and action bar
 * back-stack icons.
 */
public abstract class FragmentActivity extends BaseActivity
    implements TransactionHandler, FragmentManager.OnBackStackChangedListener {
  private boolean mDidRecreate;

  /**
   * Used to get the primary fragment container which is used to set the title for this activity.
   *
   * @return The primary fragment container resource id
   */
  protected abstract int getMainFragmentContainerResourceId();

  /**
   * Used to get the title for this particular fragment.
   *
   * @param fragment The current fragment
   * @return The title for this fragment
   */
  protected abstract String getActionBarTitle(Fragment fragment);

  /**
   * All initial Fragments for this Activity should be added here.  This ensures that Fragments
   * will
   * be handled correctly during orientation changes and other lifecycle changes.
   */
  protected abstract void addInitialFragments();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportFragmentManager().addOnBackStackChangedListener(this);

    // Handle the initial Fragment(s)
    if (savedInstanceState != null) mDidRecreate = true;
    if (!mDidRecreate) addInitialFragments();
  }

  @Override public void onAttachFragment(Fragment fragment) {
    super.onAttachFragment(fragment);
    setActionBarTitle(fragment);
  }

  @Override public boolean onSupportNavigateUp() {
    int numBack = getSupportFragmentManager().getBackStackEntryCount();
    Intent upIntent = NavUtils.getParentActivityIntent(this);
    if (numBack == 0 && upIntent != null) {
      finish();
    } else if (numBack != 0) removeCurrentFragment();
    return true;
  }

  /* -------------- TransactionHandler Callbacks -------------- */
  @Override public boolean isTablet() {
    return super.isTablet();
  }

  @Override
  public void addFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack,
      String tag) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    if (shouldAddToBackStack) {
      transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
          android.R.anim.fade_in, android.R.anim.fade_out);
    }
    transaction.replace(containerId, fragment, tag);
    if (shouldAddToBackStack) transaction.addToBackStack(null);
    transaction.commit();
  }

  @Override
  public void addFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack) {
    addFragment(fragment, containerId, shouldAddToBackStack, fragment.getClass().getName());
  }

  @Override @SuppressWarnings("unchecked")
  public <T extends Fragment> T retrieveFragment(String tag) throws ClassNotFoundException {
    T foundFragment = (T) getSupportFragmentManager().findFragmentByTag(tag);
    if (foundFragment == null) {
      throw new ClassNotFoundException(
          "The Fragment with the given tag could not be found in this Activity");
    }
    return foundFragment;
  }

  @Override public void removeCurrentFragment() {
    getSupportFragmentManager().popBackStack();
  }

  /* -------------- FragmentManager Callbacks -------------- */
  @Override public void onBackStackChanged() {
    setActionBarNavigation();
    setActionBarTitle(null);
  }

  /**
   * Sets the action bar title based on the given fragment. If null is passed, the current
   * fragment in the primary fragment container will be used.
   *
   * @param fragment The fragment to use for a title. Can be null
   */
  private void setActionBarTitle(Fragment fragment) {
    if (getSupportActionBar() != null) {
      if (fragment == null) {
        fragment = getSupportFragmentManager().
            findFragmentById(getMainFragmentContainerResourceId());
      }
      String title = getActionBarTitle(fragment);
      getSupportActionBar().setTitle(title);
    }
  }
}
