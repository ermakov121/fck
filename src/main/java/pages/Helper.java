package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class Helper {
    public static void disableHTTPSRedirectionForDomain(String domain){
        open("chrome://settings/content/insecureContent");
        actions()
                .sendKeys(Keys.TAB,Keys.TAB,Keys.TAB,Keys.ENTER,Keys.TAB,"[*.]"+domain,Keys.TAB,Keys.TAB,Keys.ENTER)
                .build()
                .perform();
    }

    private final static Properties properties;

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
    public static SelenideElement killDouble(String xpath){
        SelenideElement visibleElement = null;
        ElementsCollection list = $$(By.xpath(xpath));
        for (SelenideElement item : list){
            if (item.is(visible)){
                visibleElement = item;
            }
        }
        return visibleElement;
    }

    public static String genEmail(){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return String.valueOf(n);
    }
}