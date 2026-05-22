package AT_Scripts;
import Utils.DriverManager;
import Utils.ScreenShotLogic;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class _08_iPad_iOS_App
{
    IOSDriver driver;
    @Test(groups = {"All","Tablet","iOS_iPad","iOS_iPad_App"},
            dataProvider = "iOSiPadAppCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_iiPad_App.class,
            invocationCount = 1)
    public void iOSiPadAppSauceLabs(String dF_hub_url_Via_Xml, String model_Name, String platform_Name,
                                       String platform_Version, String app, String device_Type,
                                       Boolean is_Virtual, String iOS_iPad_App_bundle_Id, Boolean expect_Failure) throws Exception {
        boolean failureOccurred = false;
        try {
            String device_farm_hub_url = dF_hub_url_Via_Xml;
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:deviceName", model_Name);
            caps.setCapability("platformName", platform_Name);
            caps.setCapability("appium:platformVersion", platform_Version);
            caps.setCapability("appium:app", app);
            caps.setCapability("appium:deviceType", device_Type);
            caps.setCapability("appium:isVirtual", is_Virtual);
            caps.setCapability("appium:bundleId", iOS_iPad_App_bundle_Id);

            caps.setCapability("appium:automationName", "XCUITest");
            caps.setCapability("appium:autoAcceptAlerts", true); // automatically tap Allow for popups
            caps.setCapability("appium:fullReset", true);
            DriverManager.setDriver(new IOSDriver(new URL(device_farm_hub_url), caps));
            driver = DriverManager.getDriver();
            ScreenShotLogic ss = new ScreenShotLogic(driver);

            Thread.sleep(1000);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            ss.takeScreenshot("iOS_MobileApp_Simulator");
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            driver.findElement(AppiumBy.accessibilityId("test-standard_user")).click();
            ss.takeScreenshot("iOS_MobileApp_Simulator");
            driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
            ss.takeScreenshot("iOS_MobileApp_Simulator");
            driver.findElement(By.xpath("(//XCUIElementTypeOther[@name='test-ADD TO CART'])[1]")).click();
            ss.takeScreenshot("iOS_MobileApp_Simulator");
        } catch (Exception e) {
            failureOccurred = true;
            throw new RuntimeException(e);
        }finally {
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
