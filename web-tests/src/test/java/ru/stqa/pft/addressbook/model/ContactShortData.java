package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactShortData {

  private int contactID = Integer.MAX_VALUE;

  private String fname;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactShortData that = (ContactShortData) o;
    return contactID == that.contactID && Objects.equals(fname, that.fname) && Objects.equals(lname, that.lname);
  }

  @Override
  public String toString() {
    return "ContactShortData{" + "fname='" + fname + '\'' + ", lname='" + lname + '\'' + '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(contactID, fname, lname);
  }

  private String lname;

  private String phoneNumHome;
  private String phoneNumMobile;
  private String phoneNumWork;

  private String allPhones;


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
  public ContactShortData withPhoneNumHome(String phoneNumHome) {
    this.phoneNumHome = phoneNumHome;
    return this;
  }

  public ContactShortData withPhoneNumMobile(String phoneNumMobile) {
    this.phoneNumMobile = phoneNumMobile ;
    return this;
  }
  public ContactShortData withPhoneNumWork (String phoneNumWork) {
    this.phoneNumWork = phoneNumWork;
    return this;
  }

  public ContactShortData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  // геттеры

  public int getContactID() {
    return contactID;
  }
  public String getFname() {
    return fname;
  }
  public String getLname() {
    return lname;
  }

  public String getPhoneNumHome() {
    return phoneNumHome;
  }

  public String getPhoneNumMobile() {
    return phoneNumMobile;
  }

  public String getPhoneNumWork() {
    return phoneNumWork;
  }

  public String getAllPhones() {
    return allPhones;
  }
}
