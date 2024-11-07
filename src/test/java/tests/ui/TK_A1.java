package tests.ui;

import helper.GenerateData;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import pages.*;
import steps.IndividualPageSteps;

public class TK_A1 extends BaseTest {

    private final String secondName = "АВТО";
    private final String name = "ТЕСТ";
    private final String surname = "ТЕСТОВИЧ";
    private final String categoryContact = "employee";
    private final String categoryContactValue = "Сотрудник предприятия";
    private final String inn = "6321277661";
    private final String position = "менеджер";
    private final String phone = "0987654321";
    private final String email = "test" + GenerateData.genEmail() + "@mail.ru";
    private final String company = "ООО \"СЕНТЯБРИНКА\"";


    @Test
    @Description("Создание физического лица")
    public void TK_A1() {
        MainPage.selectInAllDropDownList("Физические лица");
        IndividualPage.clickCreateIndividualButton();
        IndividualPageSteps.fillFormIndividual(secondName, name, surname, categoryContact, inn, position, phone, email);
        IndividualPageSteps.checkIndividual(secondName, name, surname, categoryContactValue, company, phone, email);
    }
}
