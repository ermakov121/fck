package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdminisrationPage {
    protected static final SelenideElement configurationSettingButton = $(By.id("configphp_settings"));
    protected static final SelenideElement configurationSettingLabel = $(By.xpath("//h2[. = ' Настройка конфигурации ']"));
    protected static final SelenideElement viewLogButton = $(By.xpath("//a[. = 'Просмотр журнала']"));
    protected static final SelenideElement setCheckpointButton = $(By.name("mark"));
    protected static final SelenideElement checkAfterClickCheckpointButton = $(By.xpath("//h3[. = 'Контрольная точка в журнале установлена']"));
    protected static final SelenideElement refreshFromPointButton = $(By.xpath("//input[@value = 'Обновить с контр. точки']"));
}
