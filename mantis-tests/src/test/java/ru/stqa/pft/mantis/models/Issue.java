package ru.stqa.pft.mantis.models;

public class Issue {

  private int id;
  private String summary;

  public int getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public int withId(int id) {
    this.id = id;
    return this;
  }

  public void withDescription(String description) {
    this.description = description;
    return this;
  }

  public void with_Summary(String summary) {
    this.summary = summary;
    return this;
  }

  private String description;



}
