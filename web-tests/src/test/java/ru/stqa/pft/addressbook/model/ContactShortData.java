package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactShortData {

  private int contactID = Integer.MAX_VALUE;
  private  String fname;
  private  String lname;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactShortData that = (ContactShortData) o;
    return contactID == that.contactID && Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactID, fname, lname);
  }

 /* конструкторы уже не нужны
 public ContactShortData(int contactID, String fname, String lname) {
    this.contactID = contactID;
    this.fname = fname;
    this.lname = lname;
  }

  public ContactShortData(String fname, String lname) {
    this.contactID = 0;
    this.fname = fname;
    this.lname = lname;
  }*/

  @Override
  public String toString() {
    return "ContactShortData{" + "contactID='" + contactID + '\'' + ", fname='" + fname + '\'' + ", lname='" + lname + '\'' + '}';
  }
// сеттеры измененные

  public ContactShortData withContactID(int contactID) {
    this.contactID = contactID;
    return this;
  }

  public ContactShortData withFname(String fname) {
    this.fname = fname;
    return this;
  }

  public ContactShortData withLname(String lname) {
    this.lname = lname;
    return this;
  }
// геттеры
  public String getFname() {
    return fname;
  }

  public String getLname() {
    return lname;
  }

  public int getContactID() {
    return contactID;
  }
}
