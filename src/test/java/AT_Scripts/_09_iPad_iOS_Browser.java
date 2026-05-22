package AT_Scripts;
import Utils.DriverManager;
import Utils.ScreenShotLogic;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class _09_iPad_iOS_Browser {
    WebDriver driver;

    @Test(
            groups = {"All","Tablet","iOS_iPad","iOS_iPad_Browser"},
            dataProvider = "iOSiPadBrowserCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_iiPad_Browser.class
    )
    public void iOSiPadBrowserWikipedia(String dF_hub_url_Via_Xml, String device_Name, String platform_Name, String platform_Version,
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
            driver.get("https://www.wikipedia.org/");
            Thread.sleep(2000);
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
            Thread.sleep(2000);
            searchInput.sendKeys("iPhone");
            ss.takeScreenshot("Captured");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            ss.takeScreenshot("Captured");


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
