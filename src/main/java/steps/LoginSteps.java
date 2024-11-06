package steps;

import io.qameta.allure.Step;
import junit.framework.TestCase;
import pages.LoginPage;

public class LoginSteps extends LoginPage {
    @Step("Логин пользователя и проверка логина")
    public static void login(String login, String password){
        try{
            LoginPage.fillLoginField(login);
            LoginPage.fillPasswordField(password);
            LoginPage.clickLoginButton();
            LoginPage.checkLoginSpan();
        }
        catch (Throwable e){
            TestCase.fail("Ошибка при логине");
            e.printStackTrace();
        }
    }
}
