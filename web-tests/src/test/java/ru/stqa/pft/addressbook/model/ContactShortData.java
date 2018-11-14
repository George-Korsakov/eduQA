package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactShortData {
  private int contactID;

  public void setContactID(int contactID) {
    this.contactID = contactID;
  }

  private final String fname;
  private final String lname;


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

  public ContactShortData(int contactID, String fname, String lname) {
    this.contactID = contactID;
    this.fname = fname;
    this.lname = lname;
  }

  @Override
  public String toString() {
    return "ContactShortData{" + "contactID='" + contactID + '\'' + ", fname='" + fname + '\'' + ", lname='" + lname + '\'' + '}';
  }

  public ContactShortData(String fname, String lname) {
    this.contactID = 0;
    this.fname = fname;
    this.lname = lname;
  }

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
