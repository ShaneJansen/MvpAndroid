package com.shanejansen.mvptest.ui.mainactivity.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import com.jakewharton.rxbinding.view.RxView;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.DataManager;
import com.shanejansen.mvptest.ui.common.AppBaseFragment;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;

/**
 * Created by Shane Jansen on 3/2/17.
 */
public class RxJavaFragment extends AppBaseFragment {
  @Bind(R.id.btnLoadData) Button mBtnLoadData;
  @Bind(R.id.tvData) TextView mTvData;
  private List<Subscription> mSubscriptions;

  @Override protected int getLayoutResourceId() {
    return R.layout.fragment_rxjava;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mSubscriptions = new ArrayList<>();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View v = super.onCreateView(inflater, container, savedInstanceState);
    mSubscriptions.add(RxView.clicks(mBtnLoadData)
        .subscribe(aVoid -> DataManager.getInstance().getTestDatumRx().map(testDatum -> {
          testDatum.setText("Mapped Text " + testDatum.getId());
          return testDatum;
        }).subscribe(testDatum -> mTvData.setText(testDatum.getText()))));
    return v;
  }

  @Override public void onDestroy() {
    for (Subscription s : mSubscriptions) s.unsubscribe();
    super.onDestroy();
  }
}
