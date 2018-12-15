
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.Issue;
import ru.stqa.pft.rest.TestBase;

import java.io.IOException;


public class RestTestsDependensToIssue extends TestBase {

    private int issueIdForCheck = 0;

    @BeforeTest
    public void createIssueForCheck() throws IOException {
      int r = (int) (Math.random() * 1000000);
      Issue issue = new Issue().withSubject("new subject " + r).withDescription("new description " + r);
      issueIdForCheck = createIssue(issue);
      System.out.println("id = " + issueIdForCheck + " subject: " + issue.getSubject() + " status: " + issue.getStatus() );
    }

    @Test
    public void testCreateIssueDependToIssue() throws IOException {
    // смена статуса для проверки, потом можно убрать или закомментировать
    changeStatusIssueToClose(issueIdForCheck);
    // проверка статуса
    skipIfNotFixed(issueIdForCheck);
    System.out.println("issue fix,  Test run !");

    }

  @AfterTest
    public void cleanTest() throws IOException {
      // удаление созданного в тесте issue
      deleteIssue(issueIdForCheck);
    }

}
