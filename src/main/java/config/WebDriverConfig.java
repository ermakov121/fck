package config;

import com.codeborne.selenide.Configuration;

public class WebDriverConfig {
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/kirillermakov/chromedriver/chromedriver");

        //Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        //Configuration.browserCapabilities.setCapability("enableVNC", true); // Включить VNC
        //Configuration.browserCapabilities.setCapability("enableVideo", false); // Включить запись видео, если нужно
    }
}
