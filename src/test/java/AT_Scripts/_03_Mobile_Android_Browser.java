package AT_Scripts;

import Utils.DriverManager;
import Utils.ScreenShotLogic;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;

public class _03_Mobile_Android_Browser {
    WebDriver driver;

    @Test(
            groups = {"All","Mobile","Android_Mobile","Android_Mobile_Browser"},
            dataProvider = "androidMobileBrowserCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_AM_Browser.class
    )
    public void androidMobileBrowserPantaloonsSite(String dF_hub_url_Via_Xml, String device_Name, String platform_Name, String platform_Version,
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

            driver.get("https://www.pantaloons.com/");
            System.out.println("Opened Pantaloons in mobile browser.");
            ss.takeScreenshot("01_HomePage");

            Thread.sleep(3000);


            driver.findElement(By.cssSelector("div.mobilesearchbox")).click();
            System.out.println("Clicked.");
            ss.takeScreenshot("02_After_Click_Search_Icon");

            Thread.sleep(10000);

            driver.findElement(By.xpath("//input[@placeholder='Search for products,brands and more...']")).sendKeys("Shirt");
            System.out.println("Entered.");
            ss.takeScreenshot("03_After_Entering_Search");

            Thread.sleep(2000);

            driver.findElement(By.xpath("(//mark[text()='Shirt'])[1]")).click();
            System.out.println("Searched for item");
            ss.takeScreenshot("04_After_Search_Result_Click");

            Thread.sleep(4000);
            driver.findElement(By.cssSelector("span.cartSpriteIcon")).click();
            System.out.println("Opened Cart");
            ss.takeScreenshot("05_Cart_Page");

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








