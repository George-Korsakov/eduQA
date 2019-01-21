package ru.stqa.pft.addressbook.bdd;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupStepDefinition {

  private ApplicationManager app;
  private Groups groups;
  private GroupDate newGroup;


  @Before
  public void initApplicationManager() throws IOException {
    app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
    app.init();
  }

  @After
  public void stopApplicationManager() {
    app.stop();
    app = null;
  }

  @Given("^a set of groups$")
  public void loadGroups() {
    groups = app.db().groups();
  }
  // для передачи параметров использованы регулярые выражения
  @When("^I create a new group with name (.+), header (.+) and comment (.+) $")
  public void createGroup(String name, String header, String comment) {
    newGroup = new GroupDate().withGroupName(name).withGroupHeader(header).withGroupCommmet(comment);
    app.goTo().groupPage();
    app.group().create(newGroup);

  }

  @Then("^the new set of groups is equal to the old set with the added group$")
  public void verifyGroupCreation() {
    Groups newGroups = app.db().groups();
    assertThat(newGroups, equalTo(groups.withAdded(newGroup.withGroupId(newGroups.stream().mapToInt((g) -> g.getGroupID()).max().getAsInt()))));
  }

}
