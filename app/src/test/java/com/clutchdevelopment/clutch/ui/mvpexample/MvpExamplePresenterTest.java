package com.clutchdevelopment.clutch.ui.mvpexample;

import com.shanejansen.mvptest.data.models.TestDatum;
import com.shanejansen.mvptest.ui.mainactivity.mvpexample.MvpExampleFragment;
import com.shanejansen.mvptest.ui.mainactivity.mvpexample.MvpExamplePresenter;
import com.shanejansen.mvptest.ui.mainactivity.mvpexample.MvpExampleViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Shane Jansen on 2/1/17.
 */
@RunWith(MockitoJUnitRunner.class) public class MvpExamplePresenterTest {
  private MvpExamplePresenter mPresenter;
  private TestDatum mDatum;
  @Mock private MvpExampleViewModel mMockViewModel;
  @Mock private MvpExampleFragment mMockView;

  @Before public void init() {
    mPresenter = new MvpExamplePresenter();
    mPresenter.bindViewModel(mMockViewModel);
    mPresenter.bindView(mMockView);

    mDatum = new TestDatum(1, "This is test data");

    // Setup ViewModel
    when(mMockViewModel.getData()).thenReturn(mDatum);
    doAnswer(new Answer() {
      @Override public Object answer(InvocationOnMock invocation) throws Throwable {
        mPresenter.onLoadedData();
        return null;
      }
    }).when(mMockViewModel).loadData();
  }

  @Test public void clickedLoadData_shouldSetDataText() {
    // When
    mPresenter.clickedLoadData();

    // Then
    verify(mMockView).setDataText(mDatum.getText());
  }

  @Test public void clickedSwitchActivities_shouldSwitchActivities() {
    // When
    mPresenter.clickSwitchActivities();

    // Then
    verify(mMockView).switchActivities();
  }
}
