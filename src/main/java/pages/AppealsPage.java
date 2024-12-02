package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.junit.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AppealsPage {
    protected static final SelenideElement createAppealsButton = $(By.xpath("//div[. = 'Создать обращение']"));
    protected static final SelenideElement openFilterContact = $(By.id("btn_contact_created_by_name"));
    protected static final SelenideElement fioInputForSearchContact = $(By.id("full_name_advanced"));
    protected static final SelenideElement emailInputForSearchContact = $(By.id("email_advanced"));
    protected static final SelenideElement searchButton = $(By.id("search_form_submit"));
    protected static final SelenideElement firstContact = $(By.xpath("//table[@class = 'list view']/tbody/tr/td[1]/a"));
    protected static final SelenideElement contactField = $(By.id("contact_created_by_name"));
    protected static final SelenideElement companyField = $(By.id("account_name"));
    protected static final SelenideElement positionField = $(By.id("position"));
    protected static final SelenideElement categorySelect = $(By.id("category"));
    protected static final SelenideElement subtypeSelect = $(By.id("subtype"));
    protected static final SelenideElement subjectSelect = $(By.id("subject"));
    protected static final SelenideElement subsubjectSelect = $(By.id("subsubject"));
    protected static final SelenideElement descriptionFrame = $(By.xpath("//iframe[contains(@id, 'Area')]"));
    protected static final SelenideElement connectSelect = $(By.id("connect"));
    protected static final SelenideElement connectAnonSelect = $(By.id("connect_anon_selection"));
    protected static final SelenideElement contactEmailsSelect = $(By.id("contact_emails"));
    protected static final SelenideElement phoneField = $(By.id("connect_anon_phone"));
    protected static final SelenideElement phoneCheckField = $(By.xpath("//div[@field = 'connect_anon_phone']"));
    protected static final SelenideElement anonCheck = $(By.id("anon"));

    // Метод для ввода значения в поле "ФИО"
    public static void fillFioFieldForSearchContact(String fio) {
        fioInputForSearchContact.shouldBe(visible).setValue(fio);
    }

    // Метод для поиска значения в поле "Любой E-mail"
    public static void fillEmailFieldForSearchContact(String email) {
        emailInputForSearchContact.shouldBe(visible).setValue(email);
    }

    // Метод для клика по кнопке "Найти"
    public static void clickSearchButton() {
        searchButton.click();
    }

    // Метод для проверки кол-ва найденных контактов по заданному фильтру
    public static void checkResultsSearchContact(int expectedNumber) {
        int actualNumber = $$(By.xpath("//table[@class = 'list view']/tbody/tr")).size();
        Assert.assertEquals("Кол-во найденных контактов не равно одному", expectedNumber, actualNumber);
    }

    // Метод для клика по первому найденному контакту по фильтру
    public static void clickFirstContact() {
        firstContact.shouldBe(visible).click();
    }

    // Метод для проверки автоматического заполнения полей на форме создания обращения после поиска контакта
    public static void checkField(SelenideElement field, String expectedResult) {
        Assert.assertEquals(expectedResult, field.getValue());
    }

    // Метод для заполнения поля "Категория"
    public static void selectCategory(String value) {
        categorySelect.shouldBe(visible).selectOption(value);
    }

    // Метод для заполнения поля "Подтип"
    public static void selectSubtype(String value) {
        subtypeSelect.shouldBe(visible).selectOption(value);
    }

    // Метод для заполнения поля "Тема"
    public static void selectSubject(String value) {
        subjectSelect.shouldBe(visible).selectOption(value);
    }

    // Метод для заполнения поля "Подтема"
    public static void selectSubsubject(String value) {
        subsubjectSelect.shouldBe(visible).selectOption(value);
    }

    // Метод для заполения поля "Описание"
    public static void fillDescription(String value) {
        Selenide.switchTo().frame(descriptionFrame);
        $(By.xpath("//body")).setValue(value);
        Selenide.switchTo().parentFrame();
    }
}
