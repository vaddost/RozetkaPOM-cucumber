package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void waitUntilVisibilityOfElement(WebElement element){
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilVisibilityOfAllElements(List<WebElement> elements){
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    void waitUntilElementIsClickable(WebElement element){
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(WebDriverException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}
