package com.sr.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    public static WebDriver getDriver(DriverType type) {

        WebDriver driver = null;

        switch (type) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "WebDriver/chromedriver");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "WebDriver/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                break;
        }
        return driver;
    }
}