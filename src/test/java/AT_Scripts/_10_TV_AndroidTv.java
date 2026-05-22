package AT_Scripts;

import Utils.DriverManager;
import Utils.ScreenShotLogic;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class _10_TV_AndroidTv{
    AndroidDriver driver;
    @Test(groups = {"All","TV","AndroidTV"},
            dataProvider = "TVAndroidTvAppData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_TV_AndroidTV_App.class,
            invocationCount = 1)
    public void TVAndroidTVAppAPIDemoApp(String dF_hub_url_Via_Xml, String device_Name, String platform_Name,
                    String platform_Version, String TV_App, String device_Type,
                    Boolean is_Virtual, Boolean expect_Failure) throws Exception {
        boolean failureOccurred = false;
        try {
            String device_farm_hub_url = dF_hub_url_Via_Xml;
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("appium:deviceName", device_Name);
            caps.setCapability("platformName", platform_Name);
            caps.setCapability("appium:platformVersion", platform_Version);
            caps.setCapability("appium:app", TV_App);
            caps.setCapability("appium:deviceType", device_Type);
            caps.setCapability("appium:isVirtual", is_Virtual);
            DriverManager.setDriver(new AndroidDriver(new URL(device_farm_hub_url), caps));
            this.driver = DriverManager.getDriver();
            ScreenShotLogic ss = new ScreenShotLogic(driver);

            System.out.println("Android TV launched successfully!");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            System.out.println("Pressed DPAD_DOWN");


            driver.pressKey(new KeyEvent(AndroidKey.DPAD_CENTER));
            System.out.println("Pressed DPAD_CENTER");

            Thread.sleep(1000);

            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_CENTER));
            WebElement focusedElement = driver.findElement(By.xpath("//*[@focused='true']"));
            String focusedText = focusedElement.getText();

            System.out.println("Focused Element Text: " + focusedText);

            if (focusedText != null && !focusedText.isEmpty()) {
                System.out.println("Verification Passed: Focused element contains text");
            } else {
                System.out.println("Verification Failed: No text found");
            }

            // Back Navigation
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            System.out.println("Pressed BACK twice");

            // More Navigation
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_CENTER));
            ss.takeScreenshot("Captured");

            // Scroll through items

            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_CENTER));
            ss.takeScreenshot("Captured");
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_LEFT));
            System.out.println("Pressed LEFT");
            ss.takeScreenshot("Captured");


            driver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
            System.out.println("Pressed RIGHT");
            ss.takeScreenshot("Captured");


            driver.pressKey(new KeyEvent(AndroidKey.DPAD_UP));
            System.out.println("Pressed UP");
            ss.takeScreenshot("Captured");


            // Open Menu
            driver.pressKey(new KeyEvent(AndroidKey.MENU));
            System.out.println("Pressed MENU");
            ss.takeScreenshot("Captured");


            driver.pressKey(new KeyEvent(AndroidKey.HOME));
            System.out.println("Pressed HOME");
            ss.takeScreenshot("Captured");


        } catch (Exception e) {
            failureOccurred = true;
            throw new RuntimeException(e);
        }   finally {
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