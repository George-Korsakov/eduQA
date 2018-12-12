package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.Issue;
import ru.stqa.pft.mantis.models.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

  @Test
  public void testGetProject() throws MalformedURLException, RemoteException, ServiceException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println("Test Saop get MntisBT have Projects =  " + projects.size());
    for(Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue2").withDescription("Test issue description2").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    Assert.assertEquals(issue.getSummary(), created.getSummary());
    // toDo сделать параметром получаемый issueId
    System.out.println("Test issue id = " + created.getId() );
  }



}



