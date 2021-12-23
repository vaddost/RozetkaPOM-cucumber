package elements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class Element implements IElement{
    private WebDriver driver;
    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
        this.driver = WebDriverManager.getInstance();
    }

    @Override
    public void click(){
        try{
            moveToElement();
            webElement.click();
        } catch(ElementNotInteractableException e){
            clickWithJSExecutor();
        } catch(NoSuchElementException | StaleElementReferenceException e){
            moveToElement();
            webElement.click();
        }
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend){
        waitUntilElementIsVisible();
        moveToElement();
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        waitUntilElementIsVisible();
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return webElement.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        try{
            return webElement.findElement(by);
        } catch (StaleElementReferenceException e){
            return webElement.findElement(by);
        }

    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    public void moveToElement(){
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    public void waitUntilElementIsVisible(long timeout){
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilElementIsVisible(){
        waitUntilElementIsVisible(30);
    }

    public void clickWithJSExecutor(){
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click()", webElement
        );
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return webElement.getScreenshotAs(target);
    }
}
