package pages;

import com.codeborne.selenide.SelenideElement;
import helper.CommonHelpers;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class IndividualPage {

    protected static final SelenideElement createIndividualButton = $(By.xpath("//div[. = 'Создать физическое лицо']"));
    protected static final SelenideElement fillEnterpriseButton = $(By.id("btn_account_name"));
    protected static final SelenideElement searchCompanyButton = $(By.id("search_form_submit"));
    protected static final SelenideElement phoneInput = $(By.xpath("//input[@type = 'phone']"));
    protected static final SelenideElement saveAndExitButton = $(By.id("SAVE"));

    // Метод для перехода на форму создания нового физ. лица
    public static void clickCreateIndividualButton() {
        createIndividualButton.shouldBe(visible).click();
        $(By.xpath("//h2[@class = 'module-title-text' and contains(., 'Создать')]")).shouldBe(visible);
    }

    // Метод для заполнения поля "Телефон"
    public static void fillPhone(String phoneValue) {
        phoneInput.setValue(phoneValue);
    }

    // Метод для заполнения поля "E-mail"
    public static void fillEmail(String emailValue) {
        CommonHelpers.killDouble("//input[@type = 'email']").setValue(emailValue);
    }

    // Клик по кнопке "Сохранить и Выйти"
    public static void clickSaveAndExitButton() {
        saveAndExitButton.click();
    }
}
