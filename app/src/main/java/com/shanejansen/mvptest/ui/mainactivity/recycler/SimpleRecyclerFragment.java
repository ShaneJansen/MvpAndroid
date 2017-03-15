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
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.models.TestDatum;
import com.shanejansen.mvptest.ui.adapters.TestDatumAdapter;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shane Jansen on 2/20/17.
 */
public class SimpleRecyclerFragment extends AppBaseFragment {
  @Bind(R.id.rvRecycler) RecyclerView mRvRecycler;
  private int mCurrentId;
  private List<TestDatum> mTestData;
  private TestDatumAdapter mTestDatumAdapter;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_recycler;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCurrentId = 0;
    mTestData = new ArrayList<>();
    mTestData.add(new TestDatum(++mCurrentId, "Initial"));
    mTestData.add(new TestDatum(++mCurrentId, "Initial"));
    mTestData.add(new TestDatum(++mCurrentId, "Initial"));
    mTestDatumAdapter =
        new TestDatumAdapter(getContext(), mTestData, new TestDatumAdapter.TestDatumAdapterInf() {
          @Override public void onItemClick(int position) {
            mTestData.remove(position);
            mTestDatumAdapter.notifyItemRemoved(position);
          }
        });
    mTestDatumAdapter.notifyDataSetChanged();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    mRvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvRecycler.setAdapter(mTestDatumAdapter);
    return v;
  }

  @OnClick(R.id.fbAdd) void onClickFbAdd() {
    mTestData.add(0, new TestDatum(++mCurrentId, "Added to Beginning"));
    mTestDatumAdapter.notifyItemInserted(0);
    mRvRecycler.scrollToPosition(0);
  }

  @OnClick(R.id.fbAddListBeginning) void onClickFbAddListBeginning() {
    mTestData.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestData.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestData.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestDatumAdapter.notifyItemRangeInserted(0, 3);
    mRvRecycler.scrollToPosition(0);
  }

  @OnClick(R.id.fbAddListEnd) void onClickFbAddListEnd() {
    int oldSize = mTestData.size();
    mTestData.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestData.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestData.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestDatumAdapter.notifyItemRangeInserted(oldSize, 3);
    mRvRecycler.scrollToPosition(mTestData.size() - 1);
  }
}
