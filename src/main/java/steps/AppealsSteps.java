package steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import helper.CommonHelpers;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.AppealsPage;

public class AppealsSteps extends AppealsPage {
    @Step("Переходим к созданию нового обрашения")
    public static void clickCreateAppealsButton() {
        createAppealsButton.shouldBe(visible).click();
        CommonHelpers.checkCreateForm();
    }

    @Step("Открываем фильтр контактных лиц")
    public static void openFilterContact() {
        openFilterContact.shouldBe(visible).click();
    }

    @Step("Находим контакт по ФИО и Email и переходим в карточку контакта")
    public static void searchContact(String fio, String email, int expectedNumber) {
        fillEmailFieldForSearchContact(fio);
        fillEmailFieldForSearchContact(email);
        clickSearchButton();
        checkResultsSearchContact(expectedNumber);
        clickFirstContact();
    }

    @Step("Проверяем заполнение полей 'Контактное лицо', 'Должность' и 'Предприятие' на форме создания обращения после выбора контакта")
    public static void checkFieldsAfterSearchContact(String fio, String company, String position) {
        checkField(contactField, fio);
        checkField(companyField, company);
        checkField(positionField, position);
    }

    @Step("Заполнение полей в блоке 'Основная информация'")
    public static void fillFieldForGeneralData(String category, String subtype, String subject, String subsubject) {
        selectCategory(category);
        selectSubtype(subtype);
        selectSubject(subject);
        selectSubsubject(subsubject);
    }

    @Step("Заполнение поля 'Описание'")
    public static void fillDescriptionField(String value) {
        fillDescription(value);
    }

    @Step("Заполняем поле 'Желаемый способ связи' и 'Email для связи'")
    public static void selectConnectAndEmail(String value, String email) {
        connectSelect.shouldBe(visible).selectOption(value);
        contactEmailsSelect.selectOption(email + "(Основной)");
    }

    @Step("Заполняем поле 'Желаемый способ связи' и 'Телефон для связи'")
    public static void selectAnonConnectAndPhone(String value, String phone) {
        connectAnonSelect.shouldBe(visible).selectOption(value);
        phoneField.shouldBe(visible).setValue(phone);
    }

    @Step("Кликаем кнопку 'Сохранить и Выйти'")
    public static void clickSaveAndExit(){
        $(By.xpath("(//input[@id = 'SAVE'])[2]")).shouldBe(visible).click();
    }

    @Step("Проставляем чек бокс 'Анонимно' и проверяем, что поле 'Контактное лицо' больше не обязательно к заполнению")
    public static void checkAnonim(){
        CommonHelpers.checkRequired("Контактное лицо");
        anonCheck.shouldBe(visible).click();
        CommonHelpers.checkNotRequired("Контактное лицо");
    }

    @Step("Проверка, что перешли в нужный раздел")
    public static void checkChapter(String nameChapter){
        $(By.xpath("//h2[. = '" + nameChapter + "']")).shouldBe(visible);
    }

    @Step("Проверим, что телефон на карточке обращения совпадает с тем, который указали при создании")
    public static void checkPhone(String phone){
        Assert.assertEquals("Номера телефонов не совпадают", phone, phoneCheckField.getText());
    }
}
