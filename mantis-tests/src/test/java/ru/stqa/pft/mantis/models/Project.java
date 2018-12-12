package ru.stqa.pft.mantis.models;

import java.util.Objects;

public class Project {

  private int id;
  private String name;

  public Project withId(int id) {
    this.id =id;
    return this;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Project project = (Project) o;
    return id == project.id &&
            Objects.equals(name, project.name);
  }





}
