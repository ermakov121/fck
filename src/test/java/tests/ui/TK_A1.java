package tests.ui;

import org.junit.jupiter.api.*;
import pages.*;

public class TK_A1 extends BaseTest {

    private final String secondName = "АВТО";
    private final String name = "ТЕСТ";
    private final String surname = "ТЕСТОВИЧ";
    private final String categoryContact = "employee";
    private final String categoryContactValue = "Сотрудник предприятия";
    private final String inn = "6321277661";
    private final String position = "менеджер";
    private final String phone = "0987654321";
    private final String email = "test" + Helper.genEmail() + "@mail.ru";
    private final String company = "ООО \"СЕНТЯБРИНКА\"";

    IndividualPage individualPage = new IndividualPage();

    @Test
    public void test(){
        MainPage.selectInAllDropDownList("Физические лица");
        individualPage.clickCreateIndividualButton();
        individualPage.fillFormIndividual(secondName, name, surname, categoryContact, inn, position, phone, email);
        individualPage.checkIndividual(secondName, name, surname, categoryContactValue, company, phone, email);
    }
}
