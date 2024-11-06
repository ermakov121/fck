package config;

import com.codeborne.selenide.Configuration;

public class WebDriverConfig {
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/kirillermakov/chromedriver/chromedriver");

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        //ChromeOptions options = new ChromeOptions();
    }
}
