package business;

import pages.ProductDetailsPage;

public class AddToCartFromPDP {
    ProductDetailsPage productDetailsPage;

    public AddToCartFromPDP(){
        productDetailsPage = new ProductDetailsPage();
    }

    public AddToCartFromPDP addToCart(){
        productDetailsPage.clickBuyButton();
        return this;
    }

    public CartModalAssert check(){
        return new CartModalAssert(productDetailsPage.getCartModal());
    }
}
