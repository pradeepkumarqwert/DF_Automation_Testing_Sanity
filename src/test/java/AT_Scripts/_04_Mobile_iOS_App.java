package AT_Scripts;
import Utils.DriverManager;
import Utils.ScreenShotLogic;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
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
import java.util.Arrays;

public class _04_Mobile_iOS_App
{
    IOSDriver driver;
    Actions act;

    @Test(groups = {"All","Mobile","iOS_Mobile","iOS_Mobile_App"},
            dataProvider = "iOSMobileAppCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_iM_App.class,
            invocationCount = 1)
    public void iOSMobileAppBigBasket(String dF_hub_url_Via_Xml, String device_Name, String platform_Name, String platform_Version,
                      String app, String device_Type, Boolean is_Virtual, String iOS_iPhone_App_bundle_ID,
                      Boolean expect_Failure) throws InterruptedException {

        boolean failureOccurred = false;
        try {
            String device_farm_hub_url = dF_hub_url_Via_Xml;
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:deviceName", device_Name);
            caps.setCapability("platformName", platform_Name);
            caps.setCapability("appium:platformVersion", platform_Version);
            caps.setCapability("appium:app", app);
            caps.setCapability("appium:deviceType", device_Type);
            caps.setCapability("appium:isVirtual", is_Virtual);
            caps.setCapability("appium:bundleId", iOS_iPhone_App_bundle_ID);

            caps.setCapability("appium:automationName", "XCUITest");
            caps.setCapability("appium:autoAcceptAlerts", true); // automatically tap Allow for popups
            caps.setCapability("appium:fullReset", true);
            DriverManager.setDriver(new IOSDriver(new URL(device_farm_hub_url), caps));
            driver = DriverManager.getDriver();

            ScreenShotLogic ss = new ScreenShotLogic(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cancelBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.iOSNsPredicateString("label == 'Cancel'")
            ));
            cancelBtn.click();
            act = new Actions(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            ss.takeScreenshot("iOS_MobileApp_RealDevice");
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 271, 520));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            ss.takeScreenshot("Android_MobileApp_RealDevice");

            driver.perform(Arrays.asList(tap));
            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Continue as Guest']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Categories']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Categories']/following::XCUIElementTypeStaticText[contains(@name,'Breakfast')][2]")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            Thread.sleep(20000);
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@value='All']/following::XCUIElementTypeOther[@name='offerTag'][1]")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='addButtonProduct']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            // Tap at x=344, y=747
//            Sequence tap2 = new Sequence(finger, 2);
//            tap2.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 344, 747));
//            tap2.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//            tap2.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//
//            driver.perform(Arrays.asList(tap2));
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='decrementProductButton']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='backButton']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='backButton']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            Thread.sleep(10000);
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Categories']/following::XCUIElementTypeStaticText[contains(@name,'Breakfast')][2]")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='Breakfast Cereals']/following::XCUIElementTypeStaticText[@name='Flakes']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            Thread.sleep(20000);
//            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@value='All']/following::XCUIElementTypeOther[@name='offerTag'][1]")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='addButtonProduct']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            // Tap at x=344, y=747
//            Sequence tap3 = new Sequence(finger, 2);
//            tap2.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 344, 747));
//            tap2.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//            tap2.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.perform(Arrays.asList(tap3));
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='decrementProductButton']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
//            driver.findElement(By.xpath("//XCUIElementTypeButton[@name='backButton']")).click();
//            ss.takeScreenshot("iOS_MobileApp_RealDevice");
        }catch (NoSuchElementException | MalformedURLException e){
            failureOccurred = true;
            System.out.println("Main Catch block triggered");
            e.printStackTrace();
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
