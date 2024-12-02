package tests.ui;

import helper.GenerateData;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.BaseTest;
import pages.MainPage;
import steps.AppealsSteps;

public class TK_A5 extends BaseTest {
    private final String category = "Сотрудник ФЦК";
    private final String subtype = "Консультация";
    private final String subject = "Работа ФЦК";
    private final String subsubject = "Другое";
    private final String description = "Текст описания";
    private final String connect = "Телефон";
    private final String phone = GenerateData.genNumberForPhone();

    @Test
    @Description("Создание обращения от анонимного пользователя")
    public void TK_A5() {
        MainPage.selectInAllDropDownList("Обращения");
        AppealsSteps.clickCreateAppealsButton();
        AppealsSteps.checkAnonim();
        AppealsSteps.fillFieldForGeneralData(category, subtype, subject, subsubject);
        AppealsSteps.fillDescriptionField(description);
        AppealsSteps.selectAnonConnectAndPhone(connect, phone);
        AppealsSteps.clickSaveAndExit();
        AppealsSteps.checkChapter(" Работа ФЦК ");
        AppealsSteps.checkPhone(phone);
    }
}
