package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
// получение списка комитов из Github
  @Test
  public void testCommits() throws IOException {
    // подключние, нужно создать Personal access tokens в настройках github
    Github github = new RtGithub("364ba5b656c91af3b17ecb14799c4a80591c6ba0");
    // получаем список коммитов из указанного репозитория
    RepoCommits commits = github.repos().get(new Coordinates.Simple("George-Korsakov", "eduQA")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      // выводим список комментариев к коммитам
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }


}
