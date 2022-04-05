package io.github.sterphius;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.*;

public class GithubTest {

    private static final String
            githubUrl = "https://github.com",
            inputFieldLocator = "header-search-input",
            repositoryName = "eroshenkoam/allure-example",
            tab = "Issues";

    @BeforeEach
    void beforeEach() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "firefox";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    public void CheckGithubIssueTab() {
        Selenide.open(githubUrl);

        $(byClassName(inputFieldLocator)).click();
        $(byClassName(inputFieldLocator)).sendKeys(repositoryName);
        $(byClassName(inputFieldLocator)).submit();

        $(By.linkText(repositoryName)).click();
        $(By.partialLinkText(tab)).should(Condition.exist);
    }
}