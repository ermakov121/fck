package steps;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import helper.CommonHelpers;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
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
        //switchTo().window(CommonHelpers.getCurrentIndeTab() + 1);
    }

    @Step("Кликаем на кнопку 'Установить контрольную точку'")
    public static void clickSetCheckpointButton() {
        setCheckpointButton.shouldBe(visible).click();
        checkAfterClickCheckpointButton.shouldBe(visible);
    }

    @Step("Кликаем на кнопку 'Обновить с контр. точки'")
    public static void clickRefreshFromPointButton() {
        sleep(2000);
        refreshFromPointButton.shouldBe(visible).click();
    }

    @Step("Проверяем, что в логах системы присутствует сообщение об ошибке")
    public static void checkErrorText(String errorText) {
        $(By.xpath("//pre[contains(., '" + errorText + "')]")).shouldBe(visible);
    }
}
