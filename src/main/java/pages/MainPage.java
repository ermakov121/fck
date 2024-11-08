package pages;

import com.codeborne.selenide.SelenideElement;
import helper.CommonHelpers;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    protected static final SelenideElement allDropDownList = $(By.id("grouptab_1"));
    protected static final SelenideElement profileDropDown = $(By.id("with-label"));
    //protected static final SelenideElement adminLink = $(By.id("admin_link"));
    protected static final String admLinkXpath = "//a[@id = 'admin_link']";
    protected static final SelenideElement adminLabel = $(By.xpath("//h2[. = 'Администрирование']"));


    // Метод для открытия раздела в выпадашке "Все"
    public static void selectInAllDropDownList(String item) {
        allDropDownList.hover();
        CommonHelpers.killDouble("//li[@class = 'topnav all']//a[. = '" + item + "']").shouldBe(visible).click();
        $(By.xpath("//h2[contains(., '" + item + "')]")).shouldBe(visible);
    }

    public static void selectInProfileDropDownList(String item) {
        profileDropDown.hover();
        CommonHelpers.killDouble(admLinkXpath).click();
    }
}
