package business;

import pages.CartModal;

import java.util.List;

import static org.testng.Assert.assertTrue;


public class CartModalAssert {
    private CartModal cartModal;

    public void verifyIfProductIsPresentInCartModal(){
        cartModal = new CartModal();
        List<String> productUrlsInCartList = cartModal.getProductUrlsFromCartModal();
        String currentProductUrl = cartModal.getPageUrl();

        assertTrue(productUrlsInCartList.contains(currentProductUrl),
                "Assertion Error: Current Product Url is not found in the Cart Modal"
                );
    }

    public void verifyIfTotalPriceIsLessThanGivenValue(int expectedTotal){
        cartModal = new CartModal();
        int cartTotal = cartModal.getCartTotal();
        assertTrue(cartTotal < expectedTotal,
                "Actual total price (" + cartTotal
                        + ") is more than expected total price (" + expectedTotal + ")");
    }
}
