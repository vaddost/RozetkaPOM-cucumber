package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private WebDriverManager(){

    }

    public static void setDriver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        driverThreadLocal.set(new ChromeDriver(options));
    }

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    public static void removeInstance(){
        driverThreadLocal.remove();
    }

}
