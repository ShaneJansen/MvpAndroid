package com.shanejansen.mvptest.ui.mainactivity.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.OnClick;
import com.shanejansen.mvpandroid.adapters.SectionedRecyclerAdapter;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.models.TestDatum;
import com.shanejansen.mvptest.ui.adapters.TestSectionedDatumAdapter;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public class SectionedRecyclerFragment extends AppBaseFragment {
  @Bind(R.id.rvRecycler) RecyclerView mRvRecycler;
  List<SectionedRecyclerAdapter.Section<TestDatum>> mSections;
  TestSectionedDatumAdapter mTestSectionedDatumAdapter;
  private int mCurrentId;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_recycler;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCurrentId = 0;
    mSections = new ArrayList<>();

    List<TestDatum> section1 = new ArrayList<>();
    section1.add(new TestDatum(++mCurrentId, "Initial"));
    section1.add(new TestDatum(++mCurrentId, "Initial"));
    section1.add(new TestDatum(++mCurrentId, "Initial"));
    mSections.add(new SectionedRecyclerAdapter.Section<>("Section 1", section1));

    List<TestDatum> section2 = new ArrayList<>();
    section2.add(new TestDatum(++mCurrentId, "Initial"));
    section2.add(new TestDatum(++mCurrentId, "Initial"));
    section2.add(new TestDatum(++mCurrentId, "Initial"));
    mSections.add(new SectionedRecyclerAdapter.Section<>("Section 2", section2));

    List<TestDatum> section3 = new ArrayList<>();
    section3.add(new TestDatum(++mCurrentId, "Initial"));
    section3.add(new TestDatum(++mCurrentId, "Initial"));
    section3.add(new TestDatum(++mCurrentId, "Initial"));
    mSections.add(new SectionedRecyclerAdapter.Section<>("Section 3", section3));

    mTestSectionedDatumAdapter =
        new TestSectionedDatumAdapter(getContext(), mSections, R.layout.item_header, R.id.tvTitle,
            position -> {
              SectionedRecyclerAdapter.Section<TestDatum> section =
                  mTestSectionedDatumAdapter.positionToSection(position);
              TestDatum item = mTestSectionedDatumAdapter.positionToItem(position);
              section.getData().remove(item);
              mTestSectionedDatumAdapter.setSectionIndices();
              mTestSectionedDatumAdapter.notifyItemRemoved(position);
            });
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    mRvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvRecycler.setAdapter(mTestSectionedDatumAdapter);
    return v;
  }

  @OnClick(R.id.fbAdd) void onClickFbAdd() {
    mSections.get(0).getData().add(0, new TestDatum(++mCurrentId, "Added to Beginning"));
    mTestSectionedDatumAdapter.setSectionIndices();
    mTestSectionedDatumAdapter.notifyItemInserted(1);
    mRvRecycler.scrollToPosition(0);
  }

  @OnClick(R.id.fbAddListBeginning) void onClickFbAddListBeginning() {
    List<TestDatum> data = mSections.get(0).getData();
    data.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    data.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    data.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestSectionedDatumAdapter.setSectionIndices();
    mTestSectionedDatumAdapter.notifyItemRangeInserted(1, 3);
    mRvRecycler.scrollToPosition(0);
  }

  @OnClick(R.id.fbAddListEnd) void onClickFbAddListEnd() {
    int oldSize = mTestSectionedDatumAdapter.getTotalSize();
    List<TestDatum> data = mSections.get(mSections.size() - 1).getData();
    data.add(new TestDatum(++mCurrentId, "Added List to End"));
    data.add(new TestDatum(++mCurrentId, "Added List to End"));
    data.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestSectionedDatumAdapter.setSectionIndices();
    mTestSectionedDatumAdapter.notifyItemRangeInserted(oldSize, 3);
    mRvRecycler.scrollToPosition(mTestSectionedDatumAdapter.getTotalSize() - 1);
  }
}
