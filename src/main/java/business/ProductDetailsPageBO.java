package business;

import pages.ProductDetailsPage;

public class ProductDetailsPageBO {
    private ProductDetailsPage productDetailsPage;

    public void addProductToCart() {
        productDetailsPage = new ProductDetailsPage();
        productDetailsPage.moveToProductTitle();
        productDetailsPage.clickBuyButton();
    }
}
