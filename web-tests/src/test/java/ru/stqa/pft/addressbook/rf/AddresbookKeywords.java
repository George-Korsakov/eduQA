package ru.stqa.pft.addressbook.rf;

import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.IOException;

public class AddresbookKeywords {

  public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

  private ApplicationManager app;

  public void initApplicationManager() throws IOException {
    app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
  app.init();
  }

  public void stopApplicationManager() {
    app.stop();
    app = null;
  }

  public int getGroupCount() {
    app.goTo().groupPage();
    return app.group().getGroupCount();
  }

  public void createGroup(String name, String header, String commnet) {
    app.goTo().groupPage();
    app.group().create(new GroupDate().withGroupName(name).withGroupHeader(header).withGroupCommmet(commnet));
  }


}
