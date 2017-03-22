package com.shanejansen.mvpandroid.mvp;

import android.support.v4.app.Fragment;

/**
 * Created by Shane Jansen on 11/10/15.
 *
 * Used by the MVP view to communicate directly with its parent about transaction operations.
 */
public interface TransactionHandler {
  /**
   * Used to check if this device is a tablet.
   *
   * @return True if this device is a tablet
   */
  boolean isTablet();

  /**
   * Add a new Fragment to the current Activity.
   *
   * @param fragment Fragment to add
   * @param containerId Id of the Fragment's container
   * @param shouldAddToBackStack True if the Fragment should be added to the back stack
   * @param tag Used when the Fragment is added to the FragmentManager
   */
  void addFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack, String tag);

  /**
   * Add a new Fragment to the current Activity.
   *
   * @param fragment Fragment to add
   * @param containerId Id of the Fragment's container
   * @param shouldAddToBackStack True if the Fragment should be added to the back stack
   */
  void addFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack);

  /**
   * Removes the current Fragment from the Activity.
   * Modifies the back stack.
   */
  void removeCurrentFragment();
}
