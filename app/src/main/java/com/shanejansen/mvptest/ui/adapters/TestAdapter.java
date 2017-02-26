package com.shanejansen.mvpandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
  private boolean mValid = true;
  private int mSectionResourceId;
  private LayoutInflater mLayoutInflater;
  private RecyclerView.Adapter mBaseAdapter;
  private SparseArray<Section> mSections = new SparseArray<>();

  public TestAdapter(Context context, int sectionResourceId,
      RecyclerView.Adapter baseAdapter) {
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mSectionResourceId = sectionResourceId;
    mBaseAdapter = baseAdapter;
    mBaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override public void onChanged() {
        super.onChanged();
      }

      @Override public void onItemRangeChanged(int positionStart, int itemCount) {
        super.onItemRangeChanged(positionStart, itemCount);
      }

      @Override public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        super.onItemRangeChanged(positionStart, itemCount, payload);
      }

      @Override public void onItemRangeInserted(int positionStart, int itemCount) {
        super.onItemRangeInserted(positionStart, itemCount);
      }

      @Override public void onItemRangeRemoved(int positionStart, int itemCount) {
        super.onItemRangeRemoved(positionStart, itemCount);
      }

      @Override public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
      }
    });
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }

  public static class Section {
    int firstPosition;
    int sectionedPosition;
    CharSequence title;

    public Section(int firstPosition, CharSequence title) {
      this.firstPosition = firstPosition;
      this.title = title;
    }

    public CharSequence getTitle() {
      return title;
    }
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
