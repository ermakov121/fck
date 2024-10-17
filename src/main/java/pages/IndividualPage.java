package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.CoreMatchers.containsString;

public class IndividualPage {

    private final SelenideElement createIndividualButton = $(By.xpath("//div[. = 'Создать физическое лицо']"));
    private final SelenideElement fillEnterpriseButton = $(By.id("btn_account_name"));
    private final SelenideElement searchCompanyButton = $(By.id("search_form_submit"));
    private final SelenideElement phoneInput = $(By.xpath("//input[@type = 'phone']"));
    private final SelenideElement saveAndExitButton = $(By.id("SAVE"));

    @Step("Переходи на форму создания нового физ. лица")
    public void clickCreateIndividualButton(){
        createIndividualButton.shouldBe(visible).click();
        $(By.xpath("//h2[@class = 'module-title-text' and contains(., 'Создать')]")).shouldBe(visible);
    }

    @Step("Заполняем данные физ. лица и сохраняем")
    public void fillFormIndividual(String secondName, String name, String surname, String categoryContact, String inn, String position, String phone, String email){
        fillTextField("Фамилия", secondName);
        fillTextField("Имя", name);
        fillTextField("Отчество", surname);
        fillSelectField("contact_category", categoryContact);
        fillCompany(inn);
        fillTextField("Должность", position);
        fillPhone(phone);
        fillEmail(email);
        clickSaveAndExitButton();
        $(By.xpath("//h2[. = 'Сохранить физ. лицо']")).shouldBe(visible);
        $(By.xpath("(//input[@title = 'Сохранить и выйти'])[1]")).click();
    }

    @Step("Проверка созданного физ. лица")
    public void checkIndividual(String secondName, String name, String surname, String categoryContact, String company, String phone, String email){
        Assert.assertEquals(secondName, $(By.id("last_name")).getText());
        Assert.assertEquals(name, $(By.id("first_name")).getText());
        Assert.assertEquals(surname, $(By.id("second_name")).getText());
        Assert.assertEquals(categoryContact, $(By.xpath("//div[@field = 'contact_category']")).getText());
        Assert.assertEquals(company, $(By.id("account_id")).getText());
        Assert.assertThat($(By.xpath("//tr[@class = 'multiphone-detail']/td")).getText(), containsString(phone));
        Assert.assertEquals(email, $(By.xpath("//a[@class = 'email-link']")).getText());
    }

    private void fillTextField(String fieldName, String value){
        $(By.xpath("//div[contains(., '" + fieldName + "') and contains(@class, 'label')]/following-sibling::div/input"))
                .shouldBe(visible).setValue(value);
    }

    private void fillSelectField(String id, String value){
        $(By.id(id)).selectOptionByValue(value);
    }

    private void fillCompany(String inn){
        fillEnterpriseButton.click();
        switchTo().window(1);
        $(By.id("inn_advanced")).shouldBe(visible).setValue(inn);
        searchCompanyButton.shouldBe(visible).click();
        $(By.xpath("//tr[@class = 'oddListRowS1']/td[contains(., '" + inn + "')]")).shouldBe(visible);
        $(By.xpath("//tr[@class = 'oddListRowS1']/td/a")).click();
        switchTo().window(0);
    }

    private void fillPhone(String phoneValue){
        phoneInput.setValue(phoneValue);
    }

    private void fillEmail(String emailValue){
        Helper.killDouble("//input[@type = 'email']").setValue(emailValue);
    }

    private void clickSaveAndExitButton(){
        saveAndExitButton.click();
    }
}
