package tests.ui;

import baseTests.CreateIndividual;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import pages.MainPage;
import steps.AdministrationSteps;
import steps.AppealsSteps;
import steps.IndividualSteps;
import steps.MainSteps;

import static com.codeborne.selenide.Selenide.*;

public class TK_A3 extends CreateIndividual {
    private final String category = "Сотрудник ФЦК";
    private final String subtype = "Консультация";
    private final String subject = "Работа платформы";
    private final String subsubject = "Регистрация на портале";
    private final String description = "Текст описания";
    private final String connect = "Email";

    @Test
    @Description("Создание обращения от ФЛ без ЛК")
    public void testTK_A3() {
        IndividualSteps.checkIndividual(secondName, name, surname, categoryContactValue, company, phone, email);
        MainSteps.toAdministration();
        AdministrationSteps.clickConfigurationSetting();
        AdministrationSteps.viewLogClick();
        switchTo().window(1);
        AdministrationSteps.clickSetCheckpointButton();
        MainPage.selectInAllDropDownList("Обращения");
        AppealsSteps.clickCreateAppealsButton();
        AppealsSteps.openFilterContact();
        switchTo().window(2);
        AppealsSteps.searchContact("%" + secondName + "%", email, 1);
        switchTo().window(1);
        AppealsSteps.checkFieldsAfterSearchContact(secondName + ' ' + name + ' ' + surname, company, position);
        AppealsSteps.fillFieldForGeneralData(category, subtype, subject, subsubject);
        AppealsSteps.fillDescriptionField(description);
        AppealsSteps.selectConnectAndEmail(connect, email);
        AppealsSteps.clickSaveAndExit();
        $(By.xpath("//h2[. = ' Работа платформы ']")).shouldBe(Condition.visible);
        $(By.xpath("//div[@class = 'row detail-view-row']/div/div[contains(., 'Номер обращения')]")).shouldBe(Condition.visible);
        Selenide.switchTo().window(0);
        AdministrationSteps.viewLogClick();
        switchTo().window(2);
        AdministrationSteps.clickRefreshFromPointButton();
        AdministrationSteps.checkErrorText("SugarPHPMailer encountered an error: Invalid address");
    }
}
