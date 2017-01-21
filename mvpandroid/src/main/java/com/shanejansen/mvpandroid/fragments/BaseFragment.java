package com.shanejansen.mvpandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shanejansen.mvpandroid.handlers.TransactionHandler;

/**
 * Created by Shane Jansen on 3/25/16.
 *
 * Building block for fragments used in the MVP architecture. Ensures that the activity hosting
 * this fragment implements the default required interfaces.
 */
public abstract class BaseFragment extends Fragment {
  private TransactionHandler mTransactionHandler;
  private boolean mIsActive;
  private boolean mDidRecreate;

  /**
   * Used to get the layout resource id for this fragment.
   *
   * @return The layout resource id for this fragment
   */
  protected abstract int getLayoutResourceId();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      mTransactionHandler = (TransactionHandler) getActivity();
    } catch (ClassCastException e) {
      throw new ClassCastException(getActivity().toString() + " must implement TransactionHandler");
    }

    // Handle the initial Fragment(s) (Fragments could be nested in this Fragment)
    if (savedInstanceState != null) mDidRecreate = true;
    if (!mDidRecreate) addInitialFragments();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mIsActive = true;
    View v = inflater.inflate(getLayoutResourceId(), container, false);
    onViewInflated(v, savedInstanceState);
    return v;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mIsActive = false;
  }

  /**
   * Returns true if this Fragment's view is currently displayed.
   *
   * @return true if this Fragment's view is currently displayed
   */
  public boolean isActive() {
    return mIsActive;
  }

  /**
   * All initial Fragments for this Fragment should be added here.  This ensures that Fragments
   * will be handled correctly during orientation changes and other lifecycle changes.
   */
  protected void addInitialFragments() {
  }

  /**
   * Returns the Application's Context.
   *
   * @return The Application's Context
   */
  protected Context getAppContext() {
    return getActivity().getApplicationContext();
  }

  /**
   * Called after the view has been inflated. Any initial setup for the fragment should be done
   * here.
   */
  protected void onViewInflated(View v, Bundle savedInstanceState) {
  }

  /**
   * Returns this Fragment's Interface which is implemented by the parent. The parent could be an
   * Activity or it could be another Fragment.
   *
   * @return this Fragment's Interface which is implemented by the parent
   */
  protected <T> T getParentInterface(Class<T> clazz) {
    Object parent = getParentFragment();
    if (parent == null) parent = getActivity();
    try {
      return clazz.cast(parent);
    } catch (ClassCastException e) {
      throw new ClassCastException(
          parent.getClass().getSimpleName() + " must implement " + clazz.getSimpleName());
    }
  }

  /**
   * Returns the TransactionHandler for this Fragment. The TransactionHandler is a gateway to
   * communicate with the parent Activity.
   *
   * @return The TransactionHandler for this Fragment
   */
  public TransactionHandler getTransactionHandler() {
    return mTransactionHandler;
  }
}
