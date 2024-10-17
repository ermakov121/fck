package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement allDropDownList = $(By.id("grouptab_1"));


    // Метод для открытия раздела в выпадашке "Все"
    public void selectInAllDropDownList(String item){
        allDropDownList.hover();
        Helper.killDouble("//li[@class = 'topnav all']//a[. = '" + item + "']").shouldBe(visible).click();
        $(By.xpath("//h2[contains(., '" + item + "')]")).shouldBe(visible);
    }
}
