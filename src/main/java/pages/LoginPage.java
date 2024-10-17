package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import junit.framework.TestCase;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement loginField = $(By.id("user_name"));
    private final SelenideElement passwordField = $(By.id("username_password"));
    private final SelenideElement loginButton = $(By.id("bigbutton"));
    private final SelenideElement checkLoginSpan = $(By.xpath("//span[. = 'Мои физические лица']"));

    @Step("Логин пользователя и проверка логина")
    public LoginPage login(String login, String password){
        try{
            loginField.shouldBe(visible).setValue(login);
            passwordField.setValue(password);
            loginButton.click();
            checkLoginSpan.shouldBe(visible);
        }
        catch (Throwable e){
            TestCase.fail("Ошибка при логине");
            e.printStackTrace();
        }
        return this;
    }
}
