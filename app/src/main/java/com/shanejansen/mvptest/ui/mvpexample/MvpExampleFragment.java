package com.shanejansen.mvptest.ui.mvpexample;

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
import com.shanejansen.mvptest.ui.common.AppMvpFragment;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleFragment extends AppMvpFragment<MvpExample.PresenterForViewOps>
    implements MvpExample.ViewForPresenterOps {
  @Bind(R.id.tvData) TextView mTvData;
  private MvpExampleFragmentInf mMvpExampleFragmentInf;

  @Override protected BaseView getMvpView() {
    mMvpExampleFragmentInf = getParentInterface(MvpExampleFragmentInf.class);
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
    mMvpExampleFragmentInf = getParentInterface(MvpExampleFragmentInf.class);
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

  public interface MvpExampleFragmentInf {
    /*
      This is where you would define callback from this Fragment to the hosting Activity or
      Fragment. The hosting Activity or Fragment would define some specific operations for this
      Fragment in order to make it re-usable. For example, if this Fragment was a list, we would
      have a method here to define what happens when a list item is clicked.
     */
  }
}
