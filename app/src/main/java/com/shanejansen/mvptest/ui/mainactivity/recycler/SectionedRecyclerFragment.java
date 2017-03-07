package com.shanejansen.mvptest.ui.mainactivity.recycler;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        new TestSectionedDatumAdapter(getContext(), R.layout.item_header, R.id.tvTitle,
            new TestSectionedDatumAdapter.TestSectionedDatumAdapterInf() {
              @Override public void onItemClick(int position) {
                mSections.get(0).getData().remove(0);
                mTestSectionedDatumAdapter.updateSectionIndices();
                mTestSectionedDatumAdapter.notifyDataSetChanged();
              }
            });
    mTestSectionedDatumAdapter.setSections(mSections);
  }

  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
    mRvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvRecycler.setAdapter(mTestSectionedDatumAdapter);
  }

  @OnClick(R.id.fbAdd) void onClickFbAdd() {
    /*mTestData.add(0, new TestDatum(++mCurrentId, "Added to Beginning"));
    mTestDatumAdapter.notifyItemInserted(0);
    mRvRecycler.scrollToPosition(0);*/
  }

  @OnClick(R.id.fbAddListBeginning) void onClickFbAddListBeginning() {
    /*mTestData.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestData.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestData.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestDatumAdapter.notifyItemRangeInserted(0, 3);
    mRvRecycler.scrollToPosition(0);*/
  }

  @OnClick(R.id.fbAddListEnd) void onClickFbAddListEnd() {
    /*int oldSize = mTestData.size();
    mTestData.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestData.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestData.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestDatumAdapter.notifyItemRangeInserted(oldSize, 3);
    mRvRecycler.scrollToPosition(mTestData.size() - 1);*/
  }
}
