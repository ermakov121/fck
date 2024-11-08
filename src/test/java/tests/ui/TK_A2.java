package tests.ui;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.BaseTest;
import pages.EnterprisesPage;
import pages.MainPage;
import steps.EnterprisesSteps;

public class TK_A2 extends BaseTest {

    private final String inn = "0106008761";

    @Test
    @Description("Создание юридического лица, дубликат")
    public void testTK_A2() {
        MainPage.selectInAllDropDownList("Предприятия");
        EnterprisesSteps.clickCreateIndividualButton();
        EnterprisesSteps.fillInnField("ИНН", inn);
        EnterprisesSteps.clickGetDataFromVpButton();
    }
}
