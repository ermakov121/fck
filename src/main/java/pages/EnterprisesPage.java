package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EnterprisesPage {
    protected static final SelenideElement createEnterpriseButton = $(By.xpath("//div[. = 'Создать предприятие']"));
    protected static final SelenideElement getDataFromVp = $(By.id("get_data_vp"));

    // Метод для перехода на форму создания нового физ. лица
    public static void clickCreateIndividualButton() {
        createEnterpriseButton.shouldBe(visible).click();
        $(By.xpath("//h2[@class = 'module-title-text' and contains(., 'Создать')]")).shouldBe(visible);
    }
}
