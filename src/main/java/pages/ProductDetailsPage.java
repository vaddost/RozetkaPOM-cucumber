package pages;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage{

    @FindBy(css = ".product__buy button")
    private Button buyButton;

    @FindBy(xpath = "//h1[@class='product__title']")
    private WebElement productTitle;

    private CartModal cartModal;

    public ProductDetailsPage() {
        super();
    }

    public void clickBuyButton(){
        moveToProductTitle();
        buyButton.safeClick();
        cartModal = new CartModal();
    }

    public void moveToProductTitle(){
        waitUntilVisibilityOfElement(productTitle);
        Actions actions = new Actions(driver);
        actions.moveToElement(productTitle).perform();
    }

    public CartModal getCartModal(){
        return cartModal;
    }
}
