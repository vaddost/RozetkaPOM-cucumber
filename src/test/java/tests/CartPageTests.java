package tests;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import listeners.TestNGEventListener;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Filter;
import utils.Filters;

import java.io.File;
import java.util.*;

import static org.testng.Assert.assertTrue;

@Listeners(TestNGEventListener.class)
public class CartPageTests extends BaseTests{

    @DataProvider(name = "filters_data", parallel = true)
    public Iterator<Object[]> filtersDetails(){
        List<Object[]> res = new ArrayList<>();
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Filters.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File("src/main/resources/filters.xml");

            Filters filters = (Filters) jaxbUnmarshaller.unmarshal(xmlFile);
            List<Filter> filterList = filters.getFilters();
            for(var filter : filterList){
                res.add(new Object[]{
                        filter.getId(),
                        filter.getCategory(),
                        filter.getBrand(),
                        filter.getTotalPrice()
                });
            }
        } catch (JAXBException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return res.iterator();
    }

    @Test(dataProvider = "filters_data")
    public void testAddProductToCart(int id, String category, String brand, int totalPrice){
        var homePage = getHomePage();
        homePage.enterSearchPhrase(category);
        homePage.clickSearchButton();

        var searchResultsPage = getSearchResultsPage();
        log.info("Filter ID - "+ id + ": User is on Search Results Page for search phrase: '" + category + "'");
        searchResultsPage.enterBrandNameInSearchInput(brand);
        searchResultsPage.clickOnBrandFilterLink(brand);
        searchResultsPage.showOnlyAvailableProducts();
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
