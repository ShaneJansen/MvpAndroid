package com.shanejansen.mvptest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.shanejansen.mvpandroid.adapters.SectionedRecyclerAdapter;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.models.TestDatum;
import java.util.List;

/**
 * Created by Shane Jansen on 2/21/17.
 */
public class TestSectionedDatumAdapter extends SectionedRecyclerAdapter<TestDatum> {
  private TestSectionedDatumAdapterInf mTestSectionedDatumAdapterInf;

  public TestSectionedDatumAdapter(Context context, List<Section<TestDatum>> sections,
      int sectionResourceId, int sectionTitleResourceId,
      TestSectionedDatumAdapterInf testSectionedDatumAdapterInf) {
    super(context, sections, sectionResourceId, sectionTitleResourceId);
    mTestSectionedDatumAdapterInf = testSectionedDatumAdapterInf;
  }

  @Override protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
    View v = getLayoutInflater().inflate(R.layout.item_testdatum, parent, false);
    final ViewHolder viewHolder = new ViewHolder(v);
    v.setOnClickListener(view -> {
      if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
        mTestSectionedDatumAdapterInf.onItemClick(viewHolder.getAdapterPosition());
      }
    });
    return viewHolder;
  }

  @Override protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, TestDatum item) {
    ((ViewHolder) holder).tvId.setText(String.valueOf(item.getId()));
    ((ViewHolder) holder).tvText.setText(item.getText());
  }

  public interface TestSectionedDatumAdapterInf {
    void onItemClick(int position);
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.tvId) TextView tvId;
    @Bind(R.id.tvText) TextView tvText;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
