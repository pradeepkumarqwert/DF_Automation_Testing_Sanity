package AT_Scripts;

import Utils.DriverManager;
import Utils.ScreenShotLogic;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class _07_Tablet_Android_Browser {
    WebDriver driver;
    @Test(
            groups = {"All","Tablet","Android_Tablet","Android_Tablet_Browser"},
            dataProvider = "androidTabletBrowserCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_AT_Browser.class
    )
    public void androidTabletBrowserPantaloonsSite(String dF_hub_url_Via_Xml, String device_Name, String platform_Name, String platform_Version,
                                             String browser_Name, String device_Type, Boolean is_Virtual,
                                             Boolean expect_Failure) {

        boolean failureOccurred = false;

        try {
            String device_farm_hub_url = dF_hub_url_Via_Xml;
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:deviceName", device_Name);
            caps.setCapability("platformName", platform_Name);
            caps.setCapability("appium:platformVersion", platform_Version);
            caps.setCapability("appium:browserName", browser_Name);
            caps.setCapability("appium:deviceType", device_Type);
            caps.setCapability("appium:isVirtual", is_Virtual);

            DriverManager.setDriver(new RemoteWebDriver(new URL(device_farm_hub_url), caps));
            driver = DriverManager.getDriver();

            ScreenShotLogic ss = new ScreenShotLogic((TakesScreenshot) driver);

            driver.get("https://www.wikipedia.org");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            long endTime = System.currentTimeMillis() + 20000;
            while (System.currentTimeMillis() < endTime) {
                WebElement searchBox = wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("searchInput"))
                );
                searchBox.clear();
                searchBox.sendKeys("Software Testing");
                ss.takeScreenshot( "Value Entered");
                searchBox.submit();
                wait.until(
                        ExpectedConditions.presenceOfElementLocated(By.id("firstHeading"))
                );
                ss.takeScreenshot("SS Captured");
                driver.get("https://www.wikipedia.org");
                ss.takeScreenshot("Next iteration");

            }
            System.out.println("Page Title: " + driver.getTitle());

        } catch (Exception e) {

            failureOccurred = true;
            System.out.println("Exception occurred: " + e.getMessage());

        } finally {
            if (DriverManager.getDriver() != null) {
                DriverManager.getDriver().quit();
                DriverManager.unload();
            } else {
                System.out.println("Driver not initialized. Session creation failed.");
            }
        }


        if (expect_Failure && !failureOccurred) {
            Assert.fail("Expected failure but test PASSED");
        }

        if (!expect_Failure && failureOccurred) {
            Assert.fail("Test FAILED but was expected to PASS");
        }
    }
}








