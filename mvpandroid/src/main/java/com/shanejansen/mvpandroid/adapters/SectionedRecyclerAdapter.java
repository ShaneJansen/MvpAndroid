package com.shanejansen.mvpandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
  private LayoutInflater mLayoutInflater;
  private int mSectionResourceId, mSectionTitleResourceId;
  private int[] mSectionIndices;

  public SectionedRecyclerAdapter(Context context, List<Section<T>> sections, int sectionResourceId,
      int sectionTitleResourceId) {
    mSections = sections;
    mLayoutInflater = LayoutInflater.from(context);
    mSectionResourceId = sectionResourceId;
    mSectionTitleResourceId = sectionTitleResourceId;
    setSectionIndices();
  }

  protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent);

  protected abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, T item);

  @Override public int getItemCount() {
    int count = 0;
    for (Section s : mSections) {
      ++count;
      count += s.getData().size();
    }
    return count;
  }

  @Override public int getItemViewType(int position) {
    return isSectionHeader(position) ? TYPE_SECTION : TYPE_ITEM;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_SECTION) {
      View view = mLayoutInflater.inflate(mSectionResourceId, parent, false);
      return new SectionViewHolder(view, mSectionTitleResourceId);
    } else {
      return onCreateItemViewHolder(parent);
    }
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (isSectionHeader(position)) {
      ((SectionViewHolder) holder).title.setText(positionToSection(position).getTitle());
    } else {
      onBindItemViewHolder(holder, positionToItem(position));
    }
  }

  /**
   * Sets the indices the sections are located at.  This method should be called whenever the
   * adapter's data is manipulated and before it is notified.
   */
  public void setSectionIndices() {
    mSectionIndices = new int[mSections.size()];
    int offset = 0;
    for (int i = 0; i < mSectionIndices.length; i++) {
      mSectionIndices[i] = offset;
      offset += mSections.get(i).getData().size() + 1;
    }
  }

  /**
   * Given an adapter position, returns the corresponding item.
   *
   * @param position an adapter position
   * @return the corresponding item
   */
  public T positionToItem(int position) {
    int numItem = 0;
    for (Section<T> s : mSections) {
      ++numItem;
      for (int j = 0; j < s.getData().size(); j++) {
        if (position == numItem) {
          return s.getData().get(j);
        }
        ++numItem;
      }
    }
    return null;
  }

  /**
   * Given an adapter position, returns the corresponding section.
   *
   * @param position an adapter position
   * @return the corresponding section
   */
  public Section<T> positionToSection(int position) {
    ++position; // Since we are counting items, start at 1
    int itemNum = 0;
    for (Section<T> s : mSections) {
      itemNum += s.getData().size() + 1;
      if (position <= itemNum) return s;
    }
    return null;
  }

  /**
   * Returns the total number of items in this adapter.
   *
   * @return the total number of items in this adapter
   */
  public int getTotalSize() {
    int itemNum = 0;
    for (Section s : mSections) {
      itemNum += s.getData().size() + 1;
    }
    return itemNum;
  }

  /**
   * Returns this adapter's Layout Inflater.
   *
   * @return this adapter's Layout Inflater
   */
  protected LayoutInflater getLayoutInflater() {
    return mLayoutInflater;
  }

  /**
   * Returns true if the given adapter position is a section header; false otherwise.
   *
   * @param position an adapter position
   * @return true if the given adapter position is a section header; false otherwise
   */
  private boolean isSectionHeader(int position) {
    return Ints.contains(mSectionIndices, position);
  }

  static class SectionViewHolder extends RecyclerView.ViewHolder {
    public TextView title;

    SectionViewHolder(View itemView, int titleResourceId) {
      super(itemView);
      title = (TextView) itemView.findViewById(titleResourceId);
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
