package tests.ui;

import baseTests.CreateIndividual;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import steps.IndividualSteps;

public class TK_A1Test extends CreateIndividual {

    @Test
    @Description("Создание физического лица")
    public void testTK_A1() {
        IndividualSteps.checkIndividual(secondName, name, surname, categoryContactValue, company, phone, email);
    }
}
