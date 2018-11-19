package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;
@XStreamAlias("contact")
public class ContactShortData {
  @XStreamOmitField
  private int contactID = Integer.MAX_VALUE;
  @Expose
  private String fname;
  @Expose
  private String lname;
  @Expose
  private String phoneNumHome;
  private String phoneNumMobile;
  private String phoneNumWork;
  private String phoneNumFax;
  private String phoneNumHome2;
  private String allPhones;
  @Expose
  private  String address;
  private  String address2;
  private String alladdress;
  @Expose
  private String email;
  private String email2;
  private String email3;
  private String allemails;
  private File photo;

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
  public ContactShortData withPhoneNumHome2(String phoneNumHome2) {
    this.phoneNumHome2 = phoneNumHome2;
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
  public ContactShortData withPhoneNumFax (String phoneNumFax) {
    this.phoneNumFax = phoneNumFax;
    return this;
  }
  public ContactShortData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public ContactShortData withAddress(String address) {
    this.address = address;
    return this;
  }
  public ContactShortData withAddress2(String address2) {
    this.address2 = address2;
    return this;
  }

  public ContactShortData withAllAddress(String alladdress) {
    this.alladdress = alladdress;
    return this;
  }
  public ContactShortData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactShortData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactShortData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactShortData withAllEmails(String allemails) {
    this.allemails = allemails;
    return this;
  }
  public ContactShortData withPhoto(File photo) {
    this.photo = photo;
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
  public String getPhoneNumFax() {
    return phoneNumFax;
  }
  public String getPhoneNumHome2() {
    return phoneNumHome2;
  }
  public String getAllPhones() {
    return allPhones;
  }
  public String getAddress() {
    return address;
  }

  public String getAddress2() {
    return address2;
  }
  public String getAddressFromHomePage() {
    return alladdress;
  }
  public String getEmail() {
    return email;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getAllEmails() {
    return allemails;
  }
  public File getPhoto() {
    return photo;
  }


}
