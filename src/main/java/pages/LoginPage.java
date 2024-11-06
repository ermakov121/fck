package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    protected static final SelenideElement loginField = $(By.id("user_name"));
    protected static final SelenideElement passwordField = $(By.id("username_password"));
    protected static final SelenideElement loginButton = $(By.id("bigbutton"));
    protected static final SelenideElement checkLoginSpan = $(By.xpath("//span[. = 'Мои физические лица']"));

    // Метод для заполения поля с логином
    public static void fillLoginField(String login){
        loginField.shouldBe(visible).setValue(login);
    }

    // Метод для заполнения поля с паролем
    public static void fillPasswordField(String password){
        passwordField.shouldBe(visible).setValue(password);
    }

    // Метод нажимает кнопку логина
    public static void clickLoginButton(){
        loginButton.click();
    }

    // Метод проверяет, что логин произошел и отображается элемент на главной странице после логина
    public static void checkLoginSpan(){
        checkLoginSpan.shouldBe(visible);
    }
}
