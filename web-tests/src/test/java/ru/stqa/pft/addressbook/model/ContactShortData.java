package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addressbook")


@XStreamAlias("contact")
public class ContactShortData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int contactID = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String fname;
  @Expose
  @Column(name = "lastname")
  private String lname;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String phoneNumHome;
  @Column(name = "mobile")
  @Type(type = "text")
  private String phoneNumMobile;
  @Column(name = "work")
  @Type(type = "text")
  private String phoneNumWork;
  @Column(name = "fax")
  @Type(type = "text")
  private String phoneNumFax;
  @Type(type = "text")
  @Column(name = "phone2")
  private String phoneNumHome2;
  @Transient
  private String allPhones;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Column(name = "address2")
  @Type(type = "text")
  private String address2;
  @Transient
  private String alladdress;
  @Expose
  @Type(type = "text")
  @Column(name = "email")
  private String email;
  @Type(type = "text")
  @Column(name = "email2")
  private String email2;
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Transient
  private String allemails;
  // todo вернуть выбор группы в тесты контактов
  @Transient
  private String group;

  // todo исправить ошибку с преобраззованием типов, что бы вернуть работу с photo
  /*@Column(name = "photo")
  @Type(type ="text")
  private String photo;
*/
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
    this.phoneNumMobile = phoneNumMobile;
    return this;
  }

  public ContactShortData withPhoneNumWork(String phoneNumWork) {
    this.phoneNumWork = phoneNumWork;
    return this;
  }

  public ContactShortData withPhoneNumFax(String phoneNumFax) {
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

  public ContactShortData withGroup(String group) {
    this.group = group;
    return this;
  }
  /*public ContactShortData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }*/

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

  public String getGroup() {
    return group;
  }
  // public File getPhoto() {  return photo();  }


}
