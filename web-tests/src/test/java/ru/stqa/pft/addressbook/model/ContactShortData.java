package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactShortData {

  int contactID= Integer.MAX_VALUE;
  String fname;
  String lname;

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

  public ContactShortData withFname(String fname) {
    this.fname = fname;
    return this;
  }

  public ContactShortData withLname(String lname) {
    this.lname = lname;
    return this;
  }

  public ContactShortData withContactID(int contactID) {
    this.contactID = contactID;
    return this;
  }

  @Override
  public String toString() {
    return "ContactShortData{" + "contactID='" + contactID + '\'' + ", fname='" + fname + '\'' + ", lname='" + lname + '\'' + '}';
  }

  public String getFname(){
    return fname;
  }
  public String getLname(){
    return lname;
  }
  public int getContactID() { return contactID; }
}
