package utils;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    static Logger log = Logger.getLogger(PropertiesReader.class);
    private String chromeDriverName;
    private String url;
    private String chromeDriverPath;
    private String screenshotFolderPath;

    public PropertiesReader() {
        Properties prop = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")){
            prop.load(inputStream);
            this.chromeDriverName = prop.getProperty("CHROME_DRIVER_NAME");
            this.chromeDriverPath = prop.getProperty("CHROME_DRIVER_PATH");
            this.url = prop.getProperty("URL");
            this.screenshotFolderPath=prop.getProperty("SCREENSHOT_FOLDER_PATH");
        } catch (FileNotFoundException e) {
            log.error("config.properties file is not found in /resources folder: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e){
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getChromeDriverName() {
        return chromeDriverName;
    }

    public String getUrl() {
        return url;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public String getScreenshotFolderPath(){
        return screenshotFolderPath;
    }
}
