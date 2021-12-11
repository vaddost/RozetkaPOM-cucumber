package tests;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import listeners.TestNGEventListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Filter;

import java.io.File;
import java.util.List;

import static org.testng.Assert.assertTrue;

@Listeners(TestNGEventListener.class)
public class CartPageTests extends BaseTests{

    @DataProvider(name = "filter_data")
    public Object[][] dpMethod(){
        Object[][] resData = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Filter.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("src/main/resources/filter.xml");

            Filter filter = (Filter) jaxbUnmarshaller.unmarshal(xmlFile);
            resData = new Object[][]{{filter.getCategory(), filter.getBrand(), filter.getTotalPrice()}};
        } catch (JAXBException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return resData;
    }

    @Test(dataProvider = "filter_data")
    public void testAddProductToCart(String category, String brand, int totalPrice){
        var homePage = getHomePage();
        homePage.enterSearchPhrase(category);
        homePage.clickSearchButton();

        var searchResultsPage = getSearchResultsPage();
        log.info("User is on Search Results Page for search phrase: '" + category + "'");
        searchResultsPage.enterBrandNameInSearchInput(brand);
        searchResultsPage.clickOnBrandFilterLink(brand);
        searchResultsPage.showOnlyAvailableProducts();
        searchResultsPage.sortProductsByOption("2: expensive");
        searchResultsPage.clickOnFirstProduct();

        var productDetailsPage = getProductDetailsPage();
        log.info("User is on Product Details Page");
        String productTitle = productDetailsPage.getProductTitle();
        productDetailsPage.clickBuyButton();
        log.info("User added product to the Cart");
        List<String> productNames = productDetailsPage.getCartModal().getProductNamesFromCartModal();
        int cartTotal = productDetailsPage.getCartModal().getCartTotal();

        assertTrue(productNames.contains(productTitle),
                "Given product title is not found in the Cart product list");
        log.info("Assert: Product is added to the Cart - SUCCESS");

        assertTrue(cartTotal < totalPrice, "Actual total price (" + cartTotal
                    + ") is more than expected total price (" + totalPrice + ")");
        log.info("Assert: Actual total price is less than expected - SUCCESS");
    }
}
