package com.shanejansen.mvptest.mvpexample;

import android.os.Handler;
import android.os.Looper;
import com.shanejansen.mvpandroid.mvp.BaseViewModel;

/**
 * Created by Shane Jansen on 1/16/17.
 */
public class MvpExampleViewModel extends BaseViewModel<MvpExample.PresenterForViewModelOps>
    implements MvpExample.ViewModelForPresenterOps {
  private String mData;

  @Override public void reloadData() {
    loadData();
  }

  @Override public void loadData() {
    // Mock data load
    new Thread(new Runnable() {
      @Override public void run() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        mData = "This is some loaded data";

        // Run on main thread
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
          @Override public void run() {
            if (presenterExists()) presenter().onLoadedData();
          }
        });
      }
    }).start();
  }

  @Override public String getData() {
    return mData;
  }
}
