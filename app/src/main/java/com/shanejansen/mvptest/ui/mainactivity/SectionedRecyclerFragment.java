package com.shanejansen.mvptest.ui.mainactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.OnClick;
import com.shanejansen.mvpandroid.adapters.SectionedRecyclerAdapter;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.models.TestDatum;
import com.shanejansen.mvptest.ui.adapters.TestDatumSectionedAdapter;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public class SectionedRecyclerFragment extends AppBaseFragment {
  @Bind(R.id.rvRecycler) RecyclerView mRvRecycler;
  private int mCurrentId;
  private TestDatumSectionedAdapter mTestDatumAdapter;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_recycler;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCurrentId = 0;
    List<TestDatum> datumList1 = new ArrayList<>();
    datumList1.add(new TestDatum(++mCurrentId, "Initial"));
    datumList1.add(new TestDatum(++mCurrentId, "Initial"));
    datumList1.add(new TestDatum(++mCurrentId, "Initial"));

    List<TestDatum> datumList2 = new ArrayList<>();
    datumList2.add(new TestDatum(++mCurrentId, "Initial"));
    datumList2.add(new TestDatum(++mCurrentId, "Initial"));

    List<TestDatum> datumList3 = new ArrayList<>();
    datumList3.add(new TestDatum(++mCurrentId, "Initial"));
    datumList3.add(new TestDatum(++mCurrentId, "Initial"));
    datumList3.add(new TestDatum(++mCurrentId, "Initial"));

    SectionedRecyclerAdapter.Section<TestDatum> section1 =
        new SectionedRecyclerAdapter.Section<>("first", datumList1);
    SectionedRecyclerAdapter.Section<TestDatum> section2 =
        new SectionedRecyclerAdapter.Section<>("second", datumList2);
    SectionedRecyclerAdapter.Section<TestDatum> section3 =
        new SectionedRecyclerAdapter.Section<>("third", datumList3);

    List<SectionedRecyclerAdapter.Section<TestDatum>> sections = new ArrayList<>();
    sections.add(section1);
    sections.add(section2);
    sections.add(section3);
    mTestDatumAdapter = new TestDatumSectionedAdapter(sections);

    mTestDatumAdapter.notifyDataSetChanged();
  }

  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
    mRvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvRecycler.setAdapter(mTestDatumAdapter);
  }
}
