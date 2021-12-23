package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class CartModal extends BasePage{

    @FindBy(xpath = "//a[@class='cart-product__title']")
    private List<WebElement> productTitleLinksList;

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']/span[contains(@class, 'currency')]/preceding-sibling::span")
    private WebElement cartTotalSpan;

    public CartModal() {
        super();
    }

    public List<String> getProductUrlsFromCartModal(){
        waitUntilVisibilityOfAllElements(productTitleLinksList);
        return productTitleLinksList.stream()
                .map(x -> x.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public int getCartTotal(){
        waitUntilVisibilityOfElement(cartTotalSpan);
        return Integer.parseInt(cartTotalSpan.getText());
    }
}
