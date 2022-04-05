package io.github.sterphius;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.qameta.allure.Allure.step;

public class GithubTestWithSteps {

    private static final String
            GITHUB_URL = "https://github.com",
            INPUT_FIELD_LOCATOR = "header-search-input",
            REPOSITORY_NAME = "Sterphius/selenide-tests-with-allure",
            TAB = "Issues";

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
    public void CheckGithubIssueTabLambdaSteps() {
        step("Открываем страницу " + GITHUB_URL, () -> {
            Selenide.open(GITHUB_URL);
        });

        step("Ищем репозиторий " + REPOSITORY_NAME, () -> {
            $(byClassName(INPUT_FIELD_LOCATOR)).click();
            $(byClassName(INPUT_FIELD_LOCATOR)).sendKeys(REPOSITORY_NAME);
            $(byClassName(INPUT_FIELD_LOCATOR)).submit();
        });

        step("Открываем репозиторий " + REPOSITORY_NAME, () -> {
            $(By.linkText(REPOSITORY_NAME)).click();
        });

        step("Вкладка " + TAB + " должна отображаться", () -> {
            $(By.partialLinkText(TAB)).should(Condition.visible);
        });
    }

    @Test
    public void CheckGithubIssueTabAnnotatedSteps(){

        GithubTestWebSteps steps = new GithubTestWebSteps();

        steps.openUrl(GITHUB_URL);
        steps.findRepository(REPOSITORY_NAME);
        steps.openRepository(REPOSITORY_NAME);
        steps.checkThatTabExist(TAB);
    }
}