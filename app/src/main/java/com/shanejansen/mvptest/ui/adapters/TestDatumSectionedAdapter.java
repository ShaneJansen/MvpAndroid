package com.shanejansen.mvptest.ui.adapters;

import com.shanejansen.mvpandroid.adapters.SectionedRecyclerAdapter;
import com.shanejansen.mvptest.data.models.TestDatum;
import java.util.List;

/**
 * Created by Shane Jansen on 2/21/17.
 */
public class TestDatumSectionedAdapter extends SectionedRecyclerAdapter<TestDatum> {

  public TestDatumSectionedAdapter(List<Section<TestDatum>> sections) {
    super(sections);
  }
}
