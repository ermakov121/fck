package steps;

import static com.codeborne.selenide.Condition.*;

import io.qameta.allure.Step;
import pages.MainPage;

public class MainSteps extends MainPage {
    @Step("Переходим в раздел 'Администрирование'")
    public static void toAdministration() {
        selectInProfileDropDownList("Администрирование");
        adminLabel.shouldBe(visible);
    }
}
