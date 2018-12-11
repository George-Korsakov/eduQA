package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.models.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

  @Test
  public void testGetProject() throws MalformedURLException, RemoteException, ServiceException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println("Test Saop get MntisBT have Projects =  " + projects.size());
    for(Project project : projects) {
      System.out.println(project);
    }
  }
}
