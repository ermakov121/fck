package pages;

import com.codeborne.selenide.SelenideElement;
import helper.CommonHelpers;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private static final SelenideElement allDropDownList = $(By.id("grouptab_1"));


    // Метод для открытия раздела в выпадашке "Все"
    public static void selectInAllDropDownList(String item){
        allDropDownList.hover();
        CommonHelpers.killDouble("//li[@class = 'topnav all']//a[. = '" + item + "']").shouldBe(visible).click();
        $(By.xpath("//h2[contains(., '" + item + "')]")).shouldBe(visible);
    }
}
