package com.shanejansen.mvptest.ui.rxjavaactivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import com.jakewharton.rxbinding.view.RxView;
import com.shanejansen.mvptest.R;
import com.shanejansen.mvptest.data.DataManager;
import com.shanejansen.mvptest.ui.common.AppBaseActivity;
import java.util.ArrayList;
import java.util.List;
import rx.Subscription;

/**
 * Created by Shane Jansen on 3/2/17.
 */
public class RxJavaActivity extends AppBaseActivity {
  @Bind(R.id.btnLoadData) Button mBtnLoadData;
  @Bind(R.id.tvData) TextView mTvData;
  private List<Subscription> mSubscriptions;

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_rxjava;
  }

  @Override protected int getToolbarResourceId() {
    return R.id.toolbar;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mSubscriptions = new ArrayList<>();

    mSubscriptions.add(RxView.clicks(mBtnLoadData).subscribe(aVoid -> {
      DataManager.getInstance().getTestDatumRx().map(testDatum -> {
        testDatum.setText("Mapped Text " + testDatum.getId());
        return testDatum;
      }).subscribe(testDatum -> {
        mTvData.setText(testDatum.getText());
      });
    }));
  }

  @Override protected void onDestroy() {
    for (Subscription s : mSubscriptions) s.unsubscribe();
    super.onDestroy();
  }
}
