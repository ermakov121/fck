package helper;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CommonHelpers {
    private final static Properties properties;

    // Метод добавляет сайт в доверенные
    public static void disableHTTPSRedirectionForDomain(String domain) {
        open("chrome://settings/content/insecureContent");
        actions().sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER, Keys.TAB, "[*.]" + domain, Keys.TAB, Keys.TAB, Keys.ENTER).build().perform();
    }


    static {
        properties = new Properties();
        try {
            // Загружаем конфигурационный файл
            FileInputStream fileInput = new FileInputStream("src/main/resources/config.properties");
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при загрузке конфигурации");
        }
    }

    public static String getConfig(String key) {
        return properties.getProperty(key);
    }

    // Для отсеивания невидимых элементов на странице
    public static SelenideElement killDouble(String xpath) {
        SelenideElement visibleElement = null;
        ElementsCollection list = $$(By.xpath(xpath));
        for (SelenideElement item : list) {
            if (item.is(visible)) {
                visibleElement = item;
            }
        }
        return visibleElement;
    }

    // Метод для заполнения текстовых полей
    public static void fillTextField(String fieldName, String value){
        $(By.xpath("//div[contains(., '" + fieldName + "') and contains(@class, 'label')]/following-sibling::div/input"))
                .shouldBe(visible).setValue(value);
    }

    // Метол для заполнения полей с выпадающим списком (select)
    public static void fillSelectField(String id, String value){
        $(By.id(id)).selectOptionByValue(value);
    }

    // Методя для заполнения полей, в которых надо вводить значение и потом выбрать из предложенных
    public static void fillAndClickField(String fieldName, String value){
        $(By.xpath("//div[contains(., '" + fieldName + "') and contains(@class, 'label')]/following-sibling::div/input"))
                .shouldBe(visible).setValue(value);
        $(By.xpath("//li[. = 'менеджер']")).shouldBe(visible).click();
    }

    public static int getCurrentIndeTab(){
        String currentTab = webdriver().driver().getWebDriver().getWindowHandle();
        ArrayList<String> tabs = new ArrayList<String>(webdriver().driver().getWebDriver().getWindowHandles());
        return tabs.indexOf(currentTab);
    }
}
