import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SelenideTest {

    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "Listeners NamedBy";

    @BeforeAll
    static void setUpConfig() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @Test
        // Чистый Selenide
    void issueSearch() {
        open(BASE_URL);
        $(".header-search-input").val(REPOSITORY).submit();
        $(By.linkText(REPOSITORY)).click();
        $(withText("Issues")).click();
        $(withText(ISSUE_NAME)).should(Condition.visible);
    }

    @Test
        //Лямбда шаги через step
    void issueSearchLambdaSteps() {
        step("Открытие ссылки", () ->
                open(BASE_URL));
        step("Поиск репозитория", () ->
                $(".header-search-input").val(REPOSITORY).submit());
        step("Переход в репозиторий", () ->
                $(By.linkText(REPOSITORY)).click());
        step("Открытие вкладки Issues", () ->
                $(withText("Issues")).click());
        step("Проверка названия Issue", () ->
                $(withText(ISSUE_NAME)).should(Condition.visible));

    }

    AnnotatedSteps steps = new AnnotatedSteps();

    @Test
        //Шаги с аннотацией @Step
    void annotatedStepsTest() {
        steps.openPage(BASE_URL);
        steps.searchRep(REPOSITORY);
        steps.openRep(REPOSITORY);
        steps.tabIssues();
        steps.checkIssueName(ISSUE_NAME);
    }
}

