package com.shanejansen.mvptest.data;

import android.os.Handler;
import android.os.Looper;
import com.shanejansen.mvptest.data.models.TestDatum;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Shane Jansen on 1/19/17.
 */
public class DataManager {
  public static final int DEFAULT_SUCCESS_STATUS = 200;
  public static final int DEFAULT_FAILURE_STATUS = 500;
  private static final long REQUEST_TIME = 3000;
  private static DataManager mInstance;
  private Random mRandom;

  private DataManager() {
    mRandom = new Random();
  }

  public static DataManager getInstance() {
    if (mInstance == null) {
      mInstance = new DataManager();
    }
    return mInstance;
  }

  public void getTestDatum(final Response<TestDatum> response) {
    new Thread(() -> {
      try {
        Thread.sleep(REQUEST_TIME);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      int id = mRandom.nextInt();
      final TestDatum testDatum = new TestDatum(id, "This is datum number " + id);

      // Run on main thread
      Handler handler = new Handler(Looper.getMainLooper());
      handler.post(() -> response.success(testDatum, DEFAULT_SUCCESS_STATUS));
    }).start();
  }

  public Observable<TestDatum> getTestDatumRx() {
    int id = mRandom.nextInt();
    final TestDatum testDatum = new TestDatum(id, "This is datum number " + id);

    return
  }

  public void getTestData(final Response<List<TestDatum>> response) {
    new Thread(() -> {
      try {
        Thread.sleep(REQUEST_TIME);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      final List<TestDatum> testData = new ArrayList<>();
      for (int i = 0; i < mRandom.nextInt(20); i++) {
        int id = mRandom.nextInt();
        testData.add(new TestDatum(id, "This is datum number " + id));
      }

      // Run on main thread
      Handler handler = new Handler(Looper.getMainLooper());
      handler.post(() -> response.success(testData, DEFAULT_SUCCESS_STATUS));
    }).start();
  }

  public Observable<List<TestDatum>> getTestDataRx() {
    final List<TestDatum> testData = new ArrayList<>();
    for (int i = 0; i < mRandom.nextInt(20); i++) {
      int id = mRandom.nextInt();
      testData.add(new TestDatum(id, "This is datum number " + id));
    }

    return Observable.create(e -> {
      e.onNext(testData);
      e.onComplete(); // TODO: Never use for data calls?
    });
  }

  public interface Response<T> {
    void success(T response, int httpCode);

    void failure(String response, int httpCode);
  }
}
