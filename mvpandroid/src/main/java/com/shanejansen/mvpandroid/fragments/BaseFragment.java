package com.shanejansen.mvpandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shanejansen.mvpandroid.mvp.TransactionHandler;

/**
 * Created by Shane Jansen on 3/25/16.
 *
 * Building block for fragments used in the MVP architecture. Ensures that the activity hosting
 * this fragment implements the default required interfaces.
 */
public abstract class BaseFragment extends Fragment {
  private TransactionHandler mTransactionHandler;
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
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(getLayoutResourceId(), container, false);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  /**
   * Add a new Fragment to the current Fragment. No option to add to back stack because I can't
   * think of a reason to have a back stack for a nested Fragment (why not add to Activity if a
   * back stack is needed?
   *
   * @param fragment Fragment to add
   * @param containerId Id of the Fragment's container
   * @param shouldAnimate True if the Fragment should be added with an animation
   * @param tag Used when the Fragment is added to the FragmentManager
   */
  public void addFragment(Fragment fragment, int containerId, boolean shouldAnimate, String tag) {
    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    if (shouldAnimate) {
      transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
          android.R.anim.fade_in, android.R.anim.fade_out);
    }
    transaction.replace(containerId, fragment, tag);
    transaction.commit();
  }

  /**
   * Add a new Fragment to the current Fragment. No option to add to back stack because I can't
   * think of a reason to have a back stack for a nested Fragment (why not add to Activity if a
   * back stack is needed?
   *
   * @param fragment Fragment to add
   * @param containerId Id of the Fragment's container
   * @param shouldAnimate True if the Fragment should be added with an animation
   */
  public void addFragment(Fragment fragment, int containerId, boolean shouldAnimate) {
    addFragment(fragment, containerId, shouldAnimate, fragment.getClass().getName());
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
