package com.shanejansen.mvpandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane Jansen on 5/27/16.
 *
 * A simple Recycler adapter. Automatically updated the view when data transactions are preformed
 * on the adapter.
 */
public abstract class SimpleRecyclerAdapter<T, VH extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<VH> {
  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private List<T> mData;
  public SimpleRecyclerAdapter(Context context) {
    mContext = context;
    mLayoutInflater = LayoutInflater.from(mContext);
    mData = new ArrayList<>();
  }

  @Override public int getItemCount() {
    return mData.size();
  }

  public List<T> getData() {
    return mData;
  }

  public void clearAndAddAllData(List<T> data) {
    mData.clear();
    mData.addAll(data);
    notifyDataSetChanged();
  }

  public void addAllData(Position position, List<T> data) {
    int startPosition;
    int numAdded = data.size();
    switch (position) {
      case BEGINNING:
        startPosition = 0;
        mData.addAll(0, data);
        break;
      default:
        startPosition = mData.size();
        mData.addAll(data);
    }
    notifyItemRangeChanged(startPosition, numAdded);
  }

  public void addData(Position position, T data) {
    int insertPosition;
    switch (position) {
      case BEGINNING:
        insertPosition = 0;
        mData.add(0, data);
        break;
      default:
        insertPosition = mData.size();
        mData.add(data);
    }
    notifyItemInserted(insertPosition);
  }

  public void updateData(int index, T data) {
    mData.remove(index);
    mData.add(index, data);
    notifyItemChanged(index);
  }

  public void removeData(int index) {
    mData.remove(index);
    notifyItemRemoved(index);
  }

  public Context getContext() {
    return mContext;
  }

  public LayoutInflater getLayoutInflater() {
    return mLayoutInflater;
  }

  public enum Position {BEGINNING, END}
}
