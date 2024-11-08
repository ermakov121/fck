package steps;

import static com.codeborne.selenide.Condition.visible;
import helper.CommonHelpers;
import io.qameta.allure.Step;
import pages.EnterprisesPage;

public class EnterprisesSteps extends EnterprisesPage {

    @Step("Заполняем поле 'ИНН'")
    public static void fillInnField(String nameField, String inn){
        CommonHelpers.fillTextField(nameField, inn);
    }

    @Step("Кликаем на кнопку 'Получить данные с ВП'")
    public static void clickGetDataFromVpButton(){
        getDataFromVp.shouldBe(visible).click();
    }
}
