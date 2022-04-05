package io.github.sterphius;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class GithubTestWebSteps {

    private static final String
            INPUT_FIELD_LOCATOR = "header-search-input";

    @Step("Открываем страницу {url}")
    public void openUrl(String url) {
        Selenide.open(url);
    }

    @Step("Ищем репозиторий {repositoryName}")
    public void findRepository(String repositoryName) {
        $(byClassName(INPUT_FIELD_LOCATOR)).click();
        $(byClassName(INPUT_FIELD_LOCATOR)).sendKeys(repositoryName);
        $(byClassName(INPUT_FIELD_LOCATOR)).submit();
    }

    @Step("Открываем репозиторий {repositoryName}")
    public void openRepository(String repositoryName) {
        $(By.linkText(repositoryName)).click();
    }

    @Step("Проверяем, что вкладка {tab} существует")
    public void checkThatTabExist(String tab) {
        $(By.partialLinkText(tab)).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}