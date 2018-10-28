package ru.stqa.pft.addressbook.model;

public class ContactShortData {
  private final String fname;
  private final String lname;
  private final String address;
  private final String phoneNumHome;
  private final String email;


  public ContactShortData(String fname, String lname, String address, String phoneNumHome, String email) {
    this.fname = fname;
    this.lname = lname;
    this.address = address;
    this.phoneNumHome = phoneNumHome;
    this.email = email;

  }

  public String getFname(){
    return fname;
  }
  public String getLname(){
    return lname;
  }
  public String getAddress(){
    return address;
  }
  public String getPhoneNumHome(){
    return phoneNumHome;
  }
  public String getEmail(){
    return email;
  }

}
