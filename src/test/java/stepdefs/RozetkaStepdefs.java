package stepdefs;

import business.CartModalAssert;
import business.HomePageBO;
import business.ProductDetailsPageBO;
import business.SearchPageBO;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import utils.LoggerUtils;
import utils.PropertiesReader;
import utils.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.String.format;

public class RozetkaStepdefs {

    private static final String LOG_DIR = format("src/test/java/logs/%s-%s/%s", LocalDate.now().getMonthValue(),
            LocalDate.now().getYear(), LocalDate.now());
    private File logFile;

    private final HomePageBO homePageBO = new HomePageBO();
    private final SearchPageBO searchPageBO = new SearchPageBO();
    private final ProductDetailsPageBO productDetailsPageBO = new ProductDetailsPageBO();
    private final CartModalAssert cartModalAssert = new CartModalAssert();
    private static PropertiesReader propertiesReader;

    @BeforeAll
    public static void init(){
        propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getChromeDriverName(), propertiesReader.getChromeDriverPath());
    }

    @Before
    public void before(Scenario scenario){
        WebDriverManager.getInstance().manage().window().maximize();
        WebDriverManager.getInstance().get(propertiesReader.getUrl());

        logFile = new File(LOG_DIR,
                format("TESTLOG-%s-%s.log",
                        scenario.getName(), LocalTime.now()));
        LoggerUtils.addFileAppender(logFile);
    }

    @Given("^User search for (.*) category")
    public void userSearchForCategory(final String category) {
        homePageBO.makeSearch(category);
    }

    @Given("^User filter results by (.*) brand")
    public void userFilterResultsBy(String brand) {
        searchPageBO.filterProductsByBrand(brand);
    }

    @Given("^User sort products by price in descending order")
    public void userSortProductsByPriceInDescendingOrder() {
        searchPageBO.filterProductsByPriceDesc();
    }


    @Given("^User click on first product from grid")
    public void userClickOnFirstProductFromGrid() {
        searchPageBO.openFirstPDP();
    }


    @When("^User click Add to Cart button")
    public void userClickAddToCartButton() {
        productDetailsPageBO.addProductToCart();
    }


    @Given("^User filter to show only available products")
    public void userFilterToShowOnlyAvailableProducts() {
        searchPageBO.showOnlyAvailableProducts();
    }

    @Then("^Product is displayed in Cart modal")
    public void productIsDisplayedInCartModal() {
        cartModalAssert.verifyIfProductIsPresentInCartModal();
    }

    @Then("^Total price is less than (\\d+)")
    public void totalPriceIsLessThan(int price) {
        cartModalAssert.verifyIfTotalPriceIsLessThanGivenValue(price);
    }

    @After
    public void tearDown(){
        WebDriverManager.close();
        attachToAllure(logFile);
    }

    private void attachToAllure(File file){
        Path path = Paths.get(file.getPath());
        try(InputStream is = Files.newInputStream(path)){
            Allure.addAttachment("scenario.log", is);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
