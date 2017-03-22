package com.shanejansen.mvpandroid.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

/**
 * Created by Shane Jansen on 1/13/17.
 */
public class LoadingView extends LinearLayout {
  private static final long FADE_DURATION = 1500;
  private boolean mIsInitialLoad;
  private ProgressBar mProgressBar;
  private int mPreferredGravity;

  public LoadingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    mIsInitialLoad = true;

    // Get preferred gravity
    if (!isInEditMode()) {
      mPreferredGravity =
          attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity",
              Gravity.CENTER);
    }

    // Setup ProgressBar
    mProgressBar = new ProgressBar(context);
    mProgressBar.setIndeterminate(true);
    mProgressBar.setVisibility(GONE);
    addView(mProgressBar);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    if (!isInEditMode()) {
      loading();
      mIsInitialLoad = false;
    }
  }

  public void finishedLoading() {
    mProgressBar.setVisibility(GONE);
    setGravity(mPreferredGravity);
    setChildrenVisibility(true, true, null);
  }

  public void loading() {
    setChildrenVisibility(false, !mIsInitialLoad, new AnimationCallback() {
      @Override public void finished() {
        setGravity(Gravity.CENTER);
        mProgressBar.setVisibility(VISIBLE);
      }
    });
  }

  private void setChildrenVisibility(boolean visible, boolean animate,
      final AnimationCallback animationCallback) {
    for (int i = 0; i < getChildCount(); i++) {
      final View view = getChildAt(i);
      if (view != mProgressBar) {
        if (visible) {
          if (animate) {
            view.setAlpha(0.0f);
            view.setVisibility(View.VISIBLE);
            view.animate()
                .alpha(1.0f)
                .setDuration(FADE_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                  @Override public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (animationCallback != null) animationCallback.finished();
                  }
                });
          } else {
            view.setAlpha(1.0f);
            view.setVisibility(View.VISIBLE);
            if (animationCallback != null) animationCallback.finished();
          }
        } else {
          if (animate) {
            view.animate()
                .alpha(0.0f)
                .setDuration(FADE_DURATION)
                .setListener(new AnimatorListenerAdapter() {
                  @Override public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(GONE);
                    if (animationCallback != null) animationCallback.finished();
                  }
                });
          } else {
            view.setAlpha(0.0f);
            view.setVisibility(GONE);
            animationCallback.finished();
          }
        }
      }
    }
  }

  private interface AnimationCallback {
    void finished();
  }
}
