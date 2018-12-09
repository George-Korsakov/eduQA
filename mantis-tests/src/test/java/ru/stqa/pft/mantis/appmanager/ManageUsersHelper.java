package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageUsersHelper extends  HelperBase{
  private WebDriver wd;

  public ManageUsersHelper(ApplicationManager app){
    super(app);
    wd = app.getDriver();
  }

  public void loginAdmin (String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.xpath("//input[@value='Login']"));
  }

  public void resetUserPassword(String username){
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText(username));
    click(By.xpath("//input[@value='Reset Password']"));
    click(By.linkText("Logout"));
  }

  public void changeUserPassword(String link, String newPassword){
    wd.get(link);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.xpath("//input[@value='Update User']"));

  }

}
