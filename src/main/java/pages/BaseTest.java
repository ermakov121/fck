package pages;

import config.WebDriverConfig;
import org.junit.jupiter.api.*;
import steps.LoginSteps;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    private final String login = Helper.getConfig("login");
    private final String password = Helper.getConfig("password");
    @BeforeEach
    public void setUp(){
        WebDriverConfig.setUp();
        Helper.disableHTTPSRedirectionForDomain(Helper.getConfig("domain"));
        open(Helper.getConfig("url"));
        LoginSteps.login(login, password);
    }

    @AfterEach
    public void tearDown() {
        // Скриншот после каждого теста
        screenshot("screenshot-" + System.currentTimeMillis());
        // Закрытие браузера
        closeWebDriver();
    }
}
