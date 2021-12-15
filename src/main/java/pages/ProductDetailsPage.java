package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{

    @FindBy(css = ".product__buy button")
    private WebElement buyButton;

    @FindBy(xpath = "//h1[@class='product__title']")
    private WebElement productTitle;

    private CartModal cartModal;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void clickBuyButton(){
        waitUntilVisibilityOfElement(buyButton);

        buyButton.click();
        cartModal = new CartModal(driver);
    }

    public String getProductTitle(){
        waitUntilVisibilityOfElement(productTitle);
        Actions actions = new Actions(driver);
        actions.moveToElement(productTitle).perform();
        return productTitle.getText();
    }

    public CartModal getCartModal(){
        return cartModal;
    }
}
