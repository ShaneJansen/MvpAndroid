package com.shanejansen.mvpandroid.handlers;

/**
 * Created by Shane Jansen on 11/22/16.
 *
 * Used by the MVP parent to communicate directly with the view model about loading operations.
 */
public interface LoadingHandler {
  /**
   * Prevents the view from loading data.  reloadData() should be
   * call later to populate the view.
   *
   * Example use case: Multiple views could use the same data set
   * but only one of the views should be responsible for loading
   * the data.
   */
  void preventDataLoad();

  /**
   * Reloads this view's data.
   */
  void reloadData();
}
