package tests;

import jakarta.xml.bind.JAXBException;
import listeners.TestNGEventListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import models.Filters;
import utils.XMLObjectConverter;

import java.util.*;

import static org.testng.Assert.assertTrue;

@Listeners(TestNGEventListener.class)
public class CartPageTests extends BaseTests{

    @DataProvider(name = "filters_data", parallel = true)
    public Iterator<Object[]> filtersDetails(){
        XMLObjectConverter<Filters> xmlObjectConverter = new XMLObjectConverter<>();
        Optional<Filters> filters = Optional.empty();
        try{
            filters = Optional.ofNullable(xmlObjectConverter.convertXMLToObject("src/main/resources/filters.xml", Filters.class));
        } catch (JAXBException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return filters.orElseThrow().getFilters()
                .stream()
                .map(x -> new Object[] {
                        x.getId(),
                        x.getCategory(),
                        x.getBrand(),
                        x.getTotalPrice()
                })
                .iterator();
    }

    @Test(dataProvider = "filters_data")
    public void testAddProductToCart(int id, String category, String brand, int totalPrice){
        var homePage = getHomePage();
        homePage.enterSearchPhrase(category);
        homePage.clickSearchButton();

        var searchResultsPage = getSearchResultsPage();
        log.info("Filter ID - "+ id + ": User is on Search Results Page for search phrase: '" + category + "'");

        searchResultsPage.sortProductsByOption("2: expensive");
        searchResultsPage.clickOnFirstProduct();

        var productDetailsPage = getProductDetailsPage();
        log.info("Filter ID - "+ id + ": User is on Product Details Page");
        String productTitle = productDetailsPage.getProductTitle();
        productDetailsPage.clickBuyButton();
        log.info("Filter ID - "+ id + ": User added product to the Cart");
        List<String> productNames = productDetailsPage.getCartModal().getProductNamesFromCartModal();
        int cartTotal = productDetailsPage.getCartModal().getCartTotal();

        assertTrue(productNames.contains(productTitle),
                "Filter ID - "+ id + ": Given product title is not found in the Cart product list");
        log.info("Filter ID - "+ id + ": Assert: Product is added to the Cart - SUCCESS");

        assertTrue(cartTotal < totalPrice, "Filter ID - "+ id + ": Actual total price (" + cartTotal
                    + ") is more than expected total price (" + totalPrice + ")");
        log.info("Filter ID - "+ id + ": Assert: Actual total price is less than expected - SUCCESS");
    }
}
