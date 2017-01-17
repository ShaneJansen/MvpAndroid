package com.shanejansen.mvptest.mvpexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.shanejansen.mvpandroid.mvp.BaseView;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.common.AppMvpFragment;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleFragment extends AppMvpFragment<MvpExample.PresenterForViewOps>
    implements MvpExample.ViewForPresenterOps {
  @Bind(R.id.tvData) TextView mTvData;

  @Override protected BaseView getMvpView() {
    return this;
  }

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_mvp_example;
  }

  @Override public void setDataText(String data) {
    mTvData.setText(data);
  }

  @OnClick(R.id.btnLoadData) void btnLoadDataClicked() {
    presenter().clickedLoadData();
  }

  @Override protected void onViewInflated(View v, Bundle savedInstanceState) {
    super.onViewInflated(v, savedInstanceState);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }
}
