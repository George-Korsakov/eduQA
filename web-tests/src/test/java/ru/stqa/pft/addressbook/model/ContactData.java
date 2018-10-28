package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String fname;
  private final String mname;
  private final String lname;
  private final String nname;
  private final String photoPathToFile;
  private final String title;
  private final String company;
  private final String address;
  private final String phoneNumHome;
  private final String email;
  private final String bDay;
  private final String bMonth;
  private final String bYear;
  private  final String chosenGroup;

  public ContactData(String fname, String mname, String lname, String nname, String photoPathToFile, String title, String company, String address, String phoneNumHome, String email, String bDay, String bMonth, String bYear, String chosenGroup) {
    this.fname = fname;
    this.mname = mname;
    this.lname = lname;
    this.nname = nname;
    this.photoPathToFile = photoPathToFile;
    this.title = title;
    this.company = company;
    this.address = address;
    this.phoneNumHome = phoneNumHome;
    this.email = email;
    this.bDay = bDay;
    this.bMonth = bMonth;
    this.bYear = bYear;
    this.chosenGroup = chosenGroup;
  }

  public String getFname() {
    return fname;
  }

  public String getMname() {
    return mname;
  }

  public String getLname() {
    return lname;
  }

  public String getNname() {
    return nname;
  }

  public String getPhotoPathToFile() {
    return photoPathToFile;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getPhoneNumHome() {
    return phoneNumHome;
  }

  public String getEmail() {
    return email;
  }

  public String getbDay() {
    return bDay;
  }

  public String getbMonth() {
    return bMonth;
  }

  public String getbYear() {
    return bYear;
  }
// выбор группы
 public String getChosenGroup() {
    return chosenGroup;
  }
}
