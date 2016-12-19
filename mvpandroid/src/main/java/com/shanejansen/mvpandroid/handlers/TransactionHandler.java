package com.shanejansen.mvpandroid.handlers;

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
   * Add a new fragment to the current Activity.
   *
   * @param fragment Fragment to add
   * @param containerId Id of the fragment's container
   * @param shouldAddToBackStack True if the fragment should be added to the back stack
   * @param tag Used when the fragment is added to the FragmentManager
   */
  void addFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack, String tag);

  /**
   * Add a new fragment to the current Activity.
   *
   * @param fragment Fragment to add
   * @param containerId Id of the fragment's container
   * @param shouldAddToBackStack True if the fragment should be added to the back stack
   */
  void addFragment(Fragment fragment, int containerId, boolean shouldAddToBackStack);

  /**
   * Searches for a fragment based on the given tag. Throws and exception if the fragment could not
   * be found.
   *
   * @param tag The custom tag that was set when the fragment was added
   * @param <T> The fragment's type
   * @return The retrieved fragment
   */
  <T extends Fragment> T retrieveFragment(String tag) throws ClassNotFoundException;

  /**
   * Removes the curent fragment from the Activity.
   * Modifies the back stack.
   */
  void removeCurrentFragment();
}
