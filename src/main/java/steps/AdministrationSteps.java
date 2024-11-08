package steps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;

import helper.CommonHelpers;
import io.qameta.allure.Step;
import pages.AdminisrationPage;

public class AdministrationSteps extends AdminisrationPage {
    @Step("Переходим в настройку конфигурации")
    public static void clickConfigurationSetting() {
        configurationSettingButton.shouldBe(visible).click();
        configurationSettingLabel.shouldBe(visible);
    }

    @Step("Переходим в просмотр журнала")
    public static void viewLogClick() {
        viewLogButton.shouldBe(visible).click();
        switchTo().window(CommonHelpers.getCurrentIndeTab() + 1);
        setCheckpointButton.shouldBe(visible);
    }
}
