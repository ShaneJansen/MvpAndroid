package com.shanejansen.mvptest.ui.mainactivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
  private List<TestDatum> mTestDatum;
  private TestDatumAdapter mTestDatumAdapter;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_recycler;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mCurrentId = 0;
    mTestDatum = new ArrayList<>();
    mTestDatum.add(new TestDatum(++mCurrentId, "Initial"));
    mTestDatum.add(new TestDatum(++mCurrentId, "Initial"));
    mTestDatum.add(new TestDatum(++mCurrentId, "Initial"));
    mTestDatumAdapter =
        new TestDatumAdapter(getContext(), mTestDatum, new TestDatumAdapter.TestDatumAdapterInf() {
          @Override public void onItemClick(int position) {
            mTestDatum.remove(position);
            mTestDatumAdapter.notifyItemRemoved(position);
          }
        });
    mTestDatumAdapter.notifyDataSetChanged();
  }

  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
    mRvRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    mRvRecycler.setAdapter(mTestDatumAdapter);
  }

  @OnClick(R.id.fbAdd) void onClickFbAdd() {
    mTestDatum.add(0, new TestDatum(++mCurrentId, "Added to Beginning"));
    mTestDatumAdapter.notifyItemInserted(0);
    mRvRecycler.scrollToPosition(0);
  }

  @OnClick(R.id.fbAddListBeginning) void onClickFbAddListBeginning() {
    mTestDatum.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestDatum.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestDatum.add(0, new TestDatum(++mCurrentId, "Added List to Beginning"));
    mTestDatumAdapter.notifyItemRangeInserted(0, 3);
    mRvRecycler.scrollToPosition(0);
  }

  @OnClick(R.id.fbAddListEnd) void onClickFbAddListEnd() {
    int oldSize = mTestDatum.size();
    mTestDatum.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestDatum.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestDatum.add(new TestDatum(++mCurrentId, "Added List to End"));
    mTestDatumAdapter.notifyItemRangeInserted(oldSize, 3);
    mRvRecycler.scrollToPosition(mTestDatum.size() - 1);
  }
}
