package ru.stqa.pft.rest;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;
import java.util.Set;

public class TestBase {

  public void skipIfNotFixed(int issueId) throws IOException {
    if (!isIssueClosed(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
  private boolean isIssueClosed(int issueId) throws IOException {
    Issue issueForCheck = getIssueById(issueId);
    return issueForCheck.getStatus().equals("closed");
    //System.out.println( "Ststus test check  = " + getIssueById(id).getStatus());
  }

  private Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().toString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public int createIssue(Issue issue) throws IOException {
    // отправка методом post команды на соаздания нового issue
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", issue.getSubject()), new BasicNameValuePair("description", issue.getDescription())))
            .returnContent().asString();
    // полуение из тела ответа json
    JsonElement parsed = new JsonParser().parse(json);
    int createdIssueId = parsed.getAsJsonObject().get("issue_id").getAsInt();
    // для отладки, потом можно убрать
    System.out.println("createdIssueId " + createdIssueId);
    return createdIssueId;
  }

  private Issue getIssueById(int id) throws IOException {
    // получить issue по id
    String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/" + id +".json")).returnContent().toString();
    // для отладки потом удалить
    //System.out.println("issue for Check =  " + json);
    JsonElement parsed = new JsonParser().parse(json);
    // возвращает issue , так как в JSON ответе представлено как массив с единственным элементом
    JsonElement issue = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
    //System.out.println("test parse issue for Check == " + issue);

    return new Issue().withId(id).withStatus(issue.getAsJsonObject().get("state_name").getAsString());
  }

  // удаление issue путем изменения его статуса
  public void deleteIssue(int id) throws IOException {
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues/" + id + ".json")
            .bodyForm(new BasicNameValuePair("method","update"), new BasicNameValuePair("issue[state]", "99")))
            .returnContent().asString();
    System.out.println("Request return " + json);
  }

  // закрытие issue - изменения его статуса
  public void changeStatusIssueToClose(int id) throws IOException {
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues/" + id + ".json")
            .bodyForm(new BasicNameValuePair("method","update"), new BasicNameValuePair("issue[state]", "3")))
            .returnContent().asString();
    // для отладки
    //System.out.println(json);
    System.out.println( "Ststus test = " + getIssueById(id).getStatus());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490","");
  }
}
