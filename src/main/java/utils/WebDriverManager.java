package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverManager {
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private WebDriverManager(){

    }

    public static WebDriver getInstance(){
        if (driverThreadLocal.get() != null){
            return driverThreadLocal.get();
        }
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-notifications");
        driverThreadLocal.set(new ChromeDriver(options));
        return driverThreadLocal.get();
    }

    public static void close(){
        driverThreadLocal.get().close();
        driverThreadLocal.remove();

    }

}
