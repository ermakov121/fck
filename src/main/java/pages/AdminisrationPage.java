package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdminisrationPage {
    protected static final SelenideElement configurationSettingButton = $(By.id("configphp_settings"));
    protected static final SelenideElement configurationSettingLabel = $(By.xpath("//h2[. = ' Настройка конфигурации ']"));
    protected static final SelenideElement viewLogButton = $(By.xpath("//a[. = 'Просмотр журнала']"));
    protected static final SelenideElement setCheckpointButton = $(By.name("mark"));

}
