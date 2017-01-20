package com.shanejansen.mvptest.data.models;

/**
 * Created by Shane Jansen on 1/19/17.
 */
public class TestDatum {
  private int id;
  private String text;

  public TestDatum(int id, String text) {
    this.id = id;
    this.text = text;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
