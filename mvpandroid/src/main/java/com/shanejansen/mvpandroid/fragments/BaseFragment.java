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
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(getLayoutResourceId(), container, false);
    onViewInflated(v, savedInstanceState);
    return v;
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
   * Returns the TransactionHandler for this Fragment. The TransactionHandler is a gateway to
   * communicate with the parent Activity.
   *
   * @return The TransactionHandler for this Fragment
   */
  public TransactionHandler getTransactionHandler() {
    return mTransactionHandler;
  }
}
