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
  private List<Section> mSections;

  public SectionedRecyclerAdapter(List<Section> sections) {
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
    return super.getItemViewType(position);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  private int sectionPositionToAbsolutePosition(int position) {

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
