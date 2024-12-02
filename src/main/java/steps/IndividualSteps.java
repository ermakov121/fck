package steps;

import helper.CommonHelpers;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.IndividualPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.hamcrest.CoreMatchers.containsString;

public class IndividualSteps extends IndividualPage {

    @Step("Заполняем данные физ. лица и сохраняем")
    public static void fillFormIndividual(String secondName, String name, String surname, String categoryContact, String inn, String position, String phone, String email){
        CommonHelpers.fillTextField("Фамилия", secondName);
        CommonHelpers.fillTextField("Имя", name);
        CommonHelpers.fillTextField("Отчество", surname);
        CommonHelpers.fillSelectField("contact_category", categoryContact);
        fillCompany(inn);
        CommonHelpers.fillAndClickField("Должность", position);
        fillPhone(phone);
        fillEmail(email);
        CommonHelpers.clickSaveAndExitButton();
        $(By.xpath("//h2[. = 'Сохранить физ. лицо']")).shouldBe(visible);
        $(By.xpath("(//input[@title = 'Сохранить и выйти'])[1]")).click();
    }

    // Метод для заполнения поля "Предприятие"
    @Step("Заполняем поле 'Предприятие'")
    public static void fillCompany(String inn){
        fillEnterpriseButton.click();
        switchTo().window(1);
        $(By.id("inn_advanced")).shouldBe(visible).setValue(inn);
        searchCompanyButton.shouldBe(visible).click();
        $(By.xpath("//tr[@class = 'oddListRowS1']/td[contains(., '" + inn + "')]")).shouldBe(visible);
        $(By.xpath("//tr[@class = 'oddListRowS1']/td/a")).click();
        switchTo().window(0);
    }

    @Step("Проверка созданного физ. лица")
    public static void checkIndividual(String secondName, String name, String surname, String categoryContact, String company, String phone, String email){
        Assert.assertEquals(secondName, $(By.id("last_name")).getText());
        Assert.assertEquals(name, $(By.id("first_name")).getText());
        Assert.assertEquals(surname, $(By.id("second_name")).getText());
        Assert.assertEquals(categoryContact, $(By.xpath("//div[@field = 'contact_category']")).getText());
        Assert.assertEquals(company, $(By.id("account_id")).getText());
        Assert.assertThat($(By.xpath("//tr[@class = 'multiphone-detail']/td")).getText(), containsString(phone));
        Assert.assertEquals(email, $(By.xpath("//a[@class = 'email-link']")).getText());
    }
}
