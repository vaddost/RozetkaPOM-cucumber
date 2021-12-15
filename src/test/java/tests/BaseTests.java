package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchResultsPage;
import utils.PropertiesReader;
import utils.WebDriverManager;

public class BaseTests {

    static Logger log = Logger.getLogger(BaseTests.class);
    PropertiesReader propertiesReader;

    @BeforeMethod
    public void setUp(){
        propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getChromeDriverName(), propertiesReader.getChromeDriverPath());
        WebDriverManager.setDriver();
        WebDriverManager.getDriver().manage().window().maximize();
        //WebDriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverManager.getDriver().get(propertiesReader.getUrl());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverManager.getDriver().close();
        WebDriverManager.removeInstance();
    }

    HomePage getHomePage(){
        return new HomePage(WebDriverManager.getDriver());
    }

    ProductDetailsPage getProductDetailsPage(){
        return new ProductDetailsPage(WebDriverManager.getDriver());
    }

    SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(WebDriverManager.getDriver());
    }

}
