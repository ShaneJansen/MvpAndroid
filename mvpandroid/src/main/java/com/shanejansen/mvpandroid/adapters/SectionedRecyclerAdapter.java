package com.shanejansen.mvpandroid.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public abstract class SectionedRecyclerAdapter<T>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private static final int TYPE_SECTION = 0;
  private static final int TYPE_ITEM = 1;
  private List<Section<T>> mSections;

  public SectionedRecyclerAdapter(List<Section<T>> sections) {
    mSections = sections;
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
    getSectionIndices();
    return 0;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  private int sectionPositionToAbsolutePosition(int position) {
    return 0;
  }

  private int[] getSectionIndices() {
    int[] sectionIndices = new int[mSections.size()];
    for (int i = 0; i < mSections.size(); i++) {
      int itemsBefore = 0;
      for (int j = 0; j < i; j++) {
        itemsBefore +=
            mSections.get(j).getData().size() + 1; // Section before plus 1 for the section itself
      }
      sectionIndices[i] = itemsBefore;
    }
    return sectionIndices;
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
