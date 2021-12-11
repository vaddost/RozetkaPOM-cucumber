package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.SearchResultsPage;
import utils.PropertiesReader;

public class BaseTests {

    WebDriver driver;
    static Logger log = Logger.getLogger(BaseTests.class);
    PropertiesReader propertiesReader;

    @BeforeMethod
    public void setUp(){
        propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getChromeDriverName(), propertiesReader.getChromeDriverPath());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        log.info("ChromeDriver was started successfully.");
        driver.manage().window().maximize();
        driver.get(propertiesReader.getUrl());
        log.info("User is navigated to " + propertiesReader.getUrl());
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        log.info("ChromeDriver is closed");
    }

    HomePage getHomePage(){
        return new HomePage(driver);
    }

    ProductDetailsPage getProductDetailsPage(){
        return new ProductDetailsPage(driver);
    }

    SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(driver);
    }

}
