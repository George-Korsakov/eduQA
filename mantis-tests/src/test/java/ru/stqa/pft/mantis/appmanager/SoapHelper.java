package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.models.Issue;
import ru.stqa.pft.mantis.models.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

  private ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;

  }

  public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    // todo вынексти параметры подключения в конфигураицоный файл
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
      return  Arrays.asList(projects).stream()
              .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());

  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException, RemoteException {
    // todo вынексти параметры подключения в конфигураицоный файл
    return new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-1.2.19/api/soap/mantisconnect.php"));
  }

  public Issue addIssue(Issue issue) throws RemoteException, ServiceException, MalformedURLException {
    MantisConnectPortType mc = getMantisConnect();
    String [] categories = mc.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
    IssueData createdissueData = mc.mc_issue_get("administrator", "root", issueId);
    return new Issue().withId(createdissueData.getId().intValue()).withSummary(createdissueData.getSummary())
            .withDescription(createdissueData.getDescription()).withProject(new Project().withId(createdissueData.getProject().getId().intValue()))
            .withName(createdissueData.getProject().getName());
  }

  public IssueData getIssueById(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    MantisConnectPortType mc = getMantisConnect();
    return mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
  }
}
