package pages;

import elements.CustomFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class BasePage {
    final WebDriver driver;

    public BasePage() {
        this.driver = WebDriverManager.getInstance();
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
    }

    public String getPageUrl(){
        return driver.getCurrentUrl();
    }

    void waitUntilVisibilityOfElement(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOf(element));
    }

    void waitUntilVisibilityOfAllElements(List<WebElement> elements){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    void waitUntilElementIsClickable(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

}
