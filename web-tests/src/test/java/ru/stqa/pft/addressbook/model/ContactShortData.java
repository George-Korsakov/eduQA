package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactShortData {
  private final String fname;
  private final String lname;



  @Override
  public String toString() {
    return "ContactShortData{" + "fname='" + fname + '\'' + ", lname='" + lname + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactShortData that = (ContactShortData) o;
    return Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fname, lname);
  }

  public ContactShortData(String fname, String lname) {
    this.fname = fname;
    this.lname = lname;


  }

  public String getFname(){
    return fname;
  }
  public String getLname(){
    return lname;
  }


}
