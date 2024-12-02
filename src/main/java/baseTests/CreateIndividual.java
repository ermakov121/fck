package baseTests;

import helper.GenerateData;
import org.junit.jupiter.api.BeforeEach;
import pages.BaseTest;
import pages.IndividualPage;
import pages.MainPage;
import steps.IndividualSteps;

public class CreateIndividual extends BaseTest {
    protected final String secondName = "АВТО";
    protected final String name = "ТЕСТ";
    protected final String surname = "ТЕСТОВИЧ";
    protected final String categoryContact = "employee";
    protected final String categoryContactValue = "Сотрудник предприятия";
    protected final String inn = "6321277661";
    protected final String position = "менеджер";
    protected final String phone = "0987654321";
    protected final String email = "test" + GenerateData.genNumbersForEmail() + "@mail.ru";
    protected final String company = "ООО \"СЕНТЯБРИНКА\"";

    @BeforeEach
    public void createIndividual() {
        MainPage.selectInAllDropDownList("Физические лица");
        IndividualPage.clickCreateIndividualButton();
        IndividualSteps.fillFormIndividual(secondName, name, surname, categoryContact, inn, position, phone, email);
    }
}
