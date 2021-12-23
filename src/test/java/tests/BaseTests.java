package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.PropertiesReader;
import utils.WebDriverManager;

public class BaseTests {

    static Logger log = Logger.getLogger(BaseTests.class);
    PropertiesReader propertiesReader;

    @BeforeMethod
    public void setUp(){
        propertiesReader = new PropertiesReader();
        System.setProperty(propertiesReader.getChromeDriverName(), propertiesReader.getChromeDriverPath());

        WebDriverManager.getInstance().manage().window().maximize();
        WebDriverManager.getInstance().get(propertiesReader.getUrl());
    }

    @AfterMethod
    public void tearDown(){
        WebDriverManager.close();
    }

}
