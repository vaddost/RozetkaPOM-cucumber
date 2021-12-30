package tests;

import business.MakeSearch;
import jakarta.xml.bind.JAXBException;
import listeners.TestNGEventListener;
import models.Filter;
import org.openqa.selenium.devtools.v85.overlay.model.ColorFormat;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import models.Filters;
import pages.HomePage;
import pages.SearchResultsPage;
import utils.XMLObjectConverter;

import java.util.*;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Listeners(TestNGEventListener.class)
public class CartPageTests extends BaseTests{

    @DataProvider(name = "filters_data", parallel = true)
    public Iterator<Object[]> filtersDetails(){
        XMLObjectConverter<Filters> xmlObjectConverter = new XMLObjectConverter<>();
        Optional<Filters> filters = Optional.empty();
        try{
            filters = Optional.ofNullable(
                    xmlObjectConverter.convertXMLToObject("src/main/resources/filters.xml", Filters.class));
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
        new MakeSearch()
                .search(category)
                .filterResults(brand)
                .sortByPriceDesc()
                .navigateToFirstProductPage()
                .addToCart()
                    .check()
                        .verifyIfProductIsPresentInCartModal()
                        .verifyIfTotalPriceIsLessThanGivenValue(totalPrice);
    }

    @DataProvider(name = "filter_data")
    public Iterator<Object[]>dpFilter(){
        XMLObjectConverter<Filter> filterXMLObjectConverter = new XMLObjectConverter<>();
        Optional<Filter> filterOptional= Optional.empty();
        try {
            filterOptional = Optional.ofNullable(
                    filterXMLObjectConverter.convertXMLToObject("src/main/resources/filter.xml",Filter.class));
        } catch (JAXBException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
        Filter filter = filterOptional.orElseThrow();
        return Arrays.stream(new Object[][]{
                new Object[] {
                        filter.getCategory(),
                        filter.getBrand(),
                        filter.getTotalPrice()
                }
        }).iterator();
    }

    @Test(dataProvider = "filter_data")
    public void testShouldBeFailed(String category, String brand, int totalPrice){
        var homePage = new HomePage();
        System.out.println(homePage.getSearchButtonBackgroundColor(ColorFormat.HEX));
        System.out.println(homePage.getSearchInputPlaceholder());
        System.out.println(homePage.getSearchButtonBackgroundColor(ColorFormat.RGB));

        homePage.enterSearchPhrase(category);
        homePage.clickSearchButton();

        var searchPage = new SearchResultsPage();
        searchPage.getSellStatusCheckboxValues().forEach(System.out::println);
        fail();
    }
}
