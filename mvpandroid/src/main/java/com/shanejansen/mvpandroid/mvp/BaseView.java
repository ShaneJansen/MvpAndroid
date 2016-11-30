package com.shanejansen.mvpandroid.mvp;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Shane Jansen on 5/26/16.
 *
 * Building block for views in the MVP architecture.
 */
public interface BaseView {
  /**
   * Returns the Application's Context.
   *
   * @return The Application's Context
   */
  Context getAppContext();

  /**
   * Returns the Activity hosting this view.
   *
   * @return The Activity hosting this view
   */
  Activity getActivity();
}
