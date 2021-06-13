import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AnnotatedSteps {
    @Step("Открытие страницы {link}")
    public void openPage(String link) {
        open(link);
    }

    @Step("Поиск репозитория {repository}")
    public void searchRep(String repository) {
        $(".header-search-input").val(repository).submit();
    }

    @Step("Переход в репозиторий {repository}")
    public void openRep(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Открытие вкладки Issues")
    public void tabIssues() {
        $(withText("Issues")).click();
    }

    @Step("Проверка имени Issue")
    public void checkIssueName(String IssueName) {
        $(withText(IssueName)).should(Condition.visible);
    }

}
