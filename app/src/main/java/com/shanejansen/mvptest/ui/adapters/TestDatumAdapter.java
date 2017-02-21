package com.shanejansen.mvptest.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.models.TestDatum;
import java.util.List;

/**
 * Created by Shane Jansen on 2/21/17.
 */
public class TestDatumAdapter extends RecyclerView.Adapter<TestDatumAdapter.ViewHolder> {
  private LayoutInflater mInflater;
  private List<TestDatum> mData;
  private TestDatumAdapterInf mTestDatumAdapterInf;

  public TestDatumAdapter(Context context, List<TestDatum> data,
      TestDatumAdapterInf testDatumAdapterInf) {
    mInflater = LayoutInflater.from(context);
    mData = data;
    mTestDatumAdapterInf = testDatumAdapterInf;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = mInflater.inflate(R.layout.item_testdatum, parent, false);
    final ViewHolder viewHolder = new ViewHolder(v);
    v.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if (viewHolder.getAdapterPosition() != RecyclerView.NO_POSITION) {
          mTestDatumAdapterInf.onItemClick(viewHolder.getAdapterPosition());
        }
      }
    });
    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    TestDatum datum = mData.get(position);
    holder.tvId.setText(String.valueOf(datum.getId()));
    holder.tvText.setText(datum.getText());
  }

  @Override public int getItemCount() {
    return mData.size();
  }

  public interface TestDatumAdapterInf {
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
