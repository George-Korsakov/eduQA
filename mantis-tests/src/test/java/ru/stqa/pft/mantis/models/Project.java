package ru.stqa.pft.mantis.models;

import java.util.Objects;

public class Project {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Project project = (Project) o;
    return id == project.id &&
            Objects.equals(name, project.name);
  }

  public Project withId(int i) {
    this.id =id;
    return this;
  }

  public Project withName(String name) {
    this.name = this.name;
    return this;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  private int id;
  private String name;

}
