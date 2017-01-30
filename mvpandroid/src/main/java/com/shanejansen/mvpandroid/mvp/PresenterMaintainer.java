package com.shanejansen.mvpandroid.mvp;

import android.os.Bundle;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Shane Jansen on 5/25/16.
 *
 * Singleton used to maintain/preserve Presenters when an Activity is destroyed.
 */
public class PresenterMaintainer<P extends BasePresenter> {
  private static final String KEY_PRESENTER_ID = "presenter_id";

  private static PresenterMaintainer mInstance;
  private final AtomicLong mCurrentId;
  private final Cache<Long, P> mPresenters;

  private PresenterMaintainer(long maxSize, long expirationValue, TimeUnit expirationUnit) {
    mCurrentId = new AtomicLong();
    mPresenters = CacheBuilder.newBuilder()
        .maximumSize(maxSize)
        .expireAfterWrite(expirationValue, expirationUnit)
        .build();
  }

  public static PresenterMaintainer getInstance() {
    if (mInstance == null) {
      mInstance = new PresenterMaintainer(10, 30, TimeUnit.SECONDS);
    }
    return mInstance;
  }

  public P restorePresenter(Bundle savedInstanceState) {
    Long presenterId = savedInstanceState.getLong(KEY_PRESENTER_ID);
    P presenter = mPresenters.getIfPresent(presenterId);
    mPresenters.invalidate(presenterId);
    return presenter;
  }

  public void savePresenter(P presenter, Bundle outState) {
    long presenterId = mCurrentId.incrementAndGet();
    mPresenters.put(presenterId, presenter);
    outState.putLong(KEY_PRESENTER_ID, presenterId);
  }
}
