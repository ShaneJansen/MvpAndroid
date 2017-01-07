package com.shanejansen.mvpandroid.handlers;

/**
 * Created by Shane Jansen on 11/22/16.
 *
 * Used by the MVP parent to communicate directly with the view model about loading operations.
 */
public interface LoadingHandler {
  /**
   * Reloads this view's data.
   */
  void reloadData();
}
