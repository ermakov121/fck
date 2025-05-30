package pages;

import config.WebDriverConfig;
import helper.CommonHelpers;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import steps.LoginSteps;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    private final String login = CommonHelpers.getConfig("login");
    private final String password = CommonHelpers.getConfig("password");

    @BeforeEach
    @Step("Запускаем браузер, авторизуемся на стенде ФЦК")
    public void setUp() {
        WebDriverConfig.setUp();
        CommonHelpers.disableHTTPSRedirectionForDomain(CommonHelpers.getConfig("domain"));
        open(CommonHelpers.getConfig("url"));
        LoginSteps.login(login, password);
    }

    @AfterEach
    @Step("Закрываем браузер")
    public void tearDown() {
        // Скриншот после каждого теста
        screenshot("screenshot-" + System.currentTimeMillis());
        // Закрытие браузера
        closeWebDriver();
    }
}
