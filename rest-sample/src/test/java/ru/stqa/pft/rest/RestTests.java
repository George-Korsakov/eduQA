package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.*;

public class RestTests  {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    // создание нового  issue
    int r = (int) (Math.random() * 1000000);
    Issue newIssue = new Issue().withSubject("Test Issue #" + r).withDescription("Test description #" + r);
    int issueId = createIssue(newIssue);
    // получение списка
    Set<Issue> newIssues = getIssues();
    // проверка сравнением списков, к старому списку добавляется созданный issue
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
    System.out.println("id = " + newIssue.getId() + " subject: " + newIssue.getSubject() + " status: " + newIssue.getStatus() );
    deleteIssue(newIssue.getId());
  }

  private Set<Issue> getIssues() throws IOException {
    // выполениение запроса get для получения спсика
    String json =  getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
    // полуение из тела ответа json
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issue = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issue, new TypeToken<Set<Issue>>(){}.getType());

  }

  private int createIssue(Issue newIssue) throws IOException {
    // отправка методом post команды на соаздания нового issue
   String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
           .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()), new BasicNameValuePair("description", newIssue.getDescription())))
           .returnContent().asString();
   // полуение из тела ответа json
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();

  }
  // удаление issue путем изменения его статуса
  private void deleteIssue(int id) throws IOException {
    String json = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues/" + id + ".json")
            .bodyForm(new BasicNameValuePair("method","update"), new BasicNameValuePair("issue[state]", "99")))
            .returnContent().asString();
    // для отладки
    System.out.println(json);
  }


  // авториазция по токену
  private Executor getExecutor() {
  return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", " ");
  }
}
