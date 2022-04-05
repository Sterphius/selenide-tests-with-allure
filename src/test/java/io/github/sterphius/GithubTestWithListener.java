package io.github.sterphius;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class GithubTestWithListener {

    private static final String
            githubUrl = "https://github.com",
            inputFieldLocator = "header-search-input",
            repositoryName = "Sterphius/selenide-tests-with-allure",
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
        SelenideLogger.addListener("allure", new AllureSelenide());
        Selenide.open(githubUrl);

        $(byClassName(inputFieldLocator)).click();
        $(byClassName(inputFieldLocator)).sendKeys(repositoryName);
        $(byClassName(inputFieldLocator)).submit();

        $(By.linkText(repositoryName)).click();
        $(By.partialLinkText(tab)).should(Condition.exist);
    }
}