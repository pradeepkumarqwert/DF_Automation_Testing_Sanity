package AT_Scripts;


import Utils.DriverManager;
import Utils.ScreenShotLogic;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

public class _05_Mobile_iOS_Browser {
    WebDriver driver;
    @Test(
            groups = {"All","Mobile","iOS_Mobile","iOS_Mobile_Browser"},
            dataProvider = "iOSMobileBrowserCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_iM_Browser.class
    )
    public void iOSMobileBrowserWikipedia(String dF_hub_url_Via_Xml, String device_Name, String platform_Name, String platform_Version,
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


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            Actions actions = new Actions(driver);

            driver.get("https://www.wikipedia.org/");
            Thread.sleep(2000);
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
            Thread.sleep(2000);
            searchInput.sendKeys("iPhone");
            ss.takeScreenshot("Screenshot Captured");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            ss.takeScreenshot("Screenshot Captured");

            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));

            Thread.sleep(2000);
            System.out.println("Title after search: " + driver.getTitle());

            Thread.sleep(2000);
            actions.sendKeys(Keys.PAGE_DOWN).perform();
            Thread.sleep(1000);
            ss.takeScreenshot("Wikipedia_PageDown");

            Thread.sleep(2000);
            actions.sendKeys(Keys.PAGE_DOWN).perform();
            ss.takeScreenshot("Wikipedia_PageDown");

            Thread.sleep(2000);
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@href,'Apple')])[1]")));
            link.click();
            ss.takeScreenshot("Wikipedia_Wait_UntilElementToBE_Clickable");

            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

            Thread.sleep(2000);
            WebElement heading = driver.findElement(By.tagName("h1"));
            System.out.println("Opened page: " + heading.getText());
            ss.takeScreenshot("Wikipedia_find Element");

            Thread.sleep(2000);
            driver.navigate().back();

            Thread.sleep(2000);
            System.out.println("Now on: " + driver.getCurrentUrl());
            ss.takeScreenshot("Wikipedia_GetCurrent_URL");

            Thread.sleep(2000);
            driver.navigate().refresh();
            ss.takeScreenshot("Wikipedia_Refresh page");

            Thread.sleep(2000);

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