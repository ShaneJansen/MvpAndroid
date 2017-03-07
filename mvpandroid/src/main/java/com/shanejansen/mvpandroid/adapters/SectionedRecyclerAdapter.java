package com.shanejansen.mvpandroid.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public abstract class SectionedRecyclerAdapter<T>
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private static final int TYPE_SECTION = 0;
  private static final int TYPE_ITEM = 1;
  private SparseArray<Section<T>> mSections;
  private LayoutInflater mLayoutInflater;
  private int mSectionResourceId, mSectionTitleResourceId;

  public SectionedRecyclerAdapter(Context context, int sectionResourceId,
      int sectionTitleResourceId) {
    mSections = new SparseArray<>();
    mLayoutInflater = LayoutInflater.from(context);
    mSectionResourceId = sectionResourceId;
    mSectionTitleResourceId = sectionTitleResourceId;
  }

  protected abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent);

  protected abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, T item);

  @Override public int getItemCount() {
    int count = 0;
    for (int i = 0; i < mSections.size(); i++) {
      Section section = mSections.valueAt(i);
      if (section.getData().size() != 0) {
        ++count;
        count += section.getData().size();
      }
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
      ((SectionViewHolder) holder).title.setText(mSections.get(position).getTitle());
    } else {
      onBindItemViewHolder(holder, positionToItem(position));
    }
  }

  public void setSections(List<Section<T>> sections) {
    mSections.clear();
    for (int i = 0; i < sections.size(); i++) {
      mSections.append(i, sections.get(i));
    }
    updateSectionIndices();
    notifyDataSetChanged();
  }

  public void updateSectionIndices() {
    SparseArray<Section<T>> temp = mSections.clone();
    mSections.clear();
    int offset = 0;
    for (int i = 0; i < temp.size(); i++) {
      Section<T> section = temp.valueAt(i);
      mSections.append(offset, section);
      offset += section.getData().size() + 1;
    }
  }

  public T positionToItem(int position) {
    int numItem = 0;
    for (int i = 0; i < mSections.size(); i++) {
      ++numItem;
      Section<T> section = mSections.valueAt(i);
      for (int j = 0; j < section.getData().size(); j++) {
        if (position == numItem) {
          return section.getData().get(j);
        }
        ++numItem;
      }
    }
    return null;
  }

  public Section<T> positionToSection(int position) {
    return mSections.get(position);
  }

  protected LayoutInflater getLayoutInflater() {
    return mLayoutInflater;
  }

  private boolean isSectionHeader(int position) {
    return mSections.get(position) != null;
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
