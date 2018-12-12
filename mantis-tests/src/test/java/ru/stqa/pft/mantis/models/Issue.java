package ru.stqa.pft.mantis.models;

import biz.futureware.mantis.rpc.soap.client.IssueData;

public class Issue {

  private int id;
  private String summary;
  private String description;
  private String name;
  private Project project;
  private String category;
  private String status;

  public Issue(){}

  public Issue(IssueData issueData){
    id = issueData.getId().intValue();
    summary = issueData.getSummary();
    description = issueData.getDescription();
    category = issueData.getCategory();
    status = issueData.getStatus().getName();
  }

  public Project getProject() {
    return project;
  }

  public int getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  @Override
  public String toString() {
    return "Issue{" +
            "status='" + status + '\'' +
            '}';
  }

  public String getDescription() {
    return description;
  }
  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public String getStatus() {
    return status;
  }


  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public Issue withName(String name) {
    this.name = name;
    return this;
  }

  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }

  public Issue withCategory(String category) {
    this.category = category;
    return this;
  }

  public Issue withStatus(String status) {
    this.status = status;
    return this;
  }




}
