package tests.ui;

import baseTests.CreateIndividual;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import steps.AdministrationSteps;
import steps.IndividualSteps;
import steps.MainSteps;

import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.Selenide.webdriver;

public class TK_A3 extends CreateIndividual {
    @Test
    public void testTK_A3() {
        IndividualSteps.checkIndividual(secondName, name, surname, categoryContactValue, company, phone, email);
        MainSteps.toAdministration();
        AdministrationSteps.clickConfigurationSetting();
        AdministrationSteps.viewLogClick();
    }
}
