package com.shanejansen.mvpandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.common.primitives.Ints;
import java.util.List;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public abstract class SectionedRecyclerAdapter<T>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private static final int TYPE_SECTION = 0;
  private static final int TYPE_ITEM = 1;
  private List<Section<T>> mSections;
  private int[] mSectionIndices;
  private Context mContext;
  private LayoutInflater mLayoutInflater;

  abstract int getItemLayoutId();

  abstract int getHeaderLayoutId();

  abstract

  public SectionedRecyclerAdapter(Context context, List<Section<T>> sections) {
    mSections = sections;
    mContext = context;
    mLayoutInflater = LayoutInflater.from(mContext);
    setSectionIndices();
  }

  @Override public int getItemCount() {
    int count = 0;
    count += mSections.size();
    for (Section s : mSections) {
      count += s.getData().size();
    }
    return count;
  }

  @Override public int getItemViewType(int position) {
    if (Ints.contains(mSectionIndices, position)) {
      return TYPE_SECTION;
    } else {
      return TYPE_ITEM;
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = m
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  private int sectionPositionToAbsolutePosition(int position) {
    return 0;
  }

  private void setSectionIndices() {
    mSectionIndices = new int[mSections.size()];
    for (int i = 0; i < mSections.size(); i++) {
      int itemsBefore = 0;
      for (int j = 0; j < i; j++) {
        itemsBefore +=
            mSections.get(j).getData().size() + 1; // Section before plus 1 for the section itself
      }
      mSectionIndices[i] = itemsBefore;
    }
  }

  static class SectionViewHolder extends RecyclerView.ViewHolder {
    SectionViewHolder(View itemView) {
      super(itemView);
    }
  }

  public static class Section<T> {
    private String mTitle;
    private List<T> mData;

    public Section(String title, List<T> data) {
      mTitle = title;
      mData = data;
    }

    public String getTitle() {
      return mTitle;
    }

    public List<T> getData() {
      return mData;
    }
  }
}
