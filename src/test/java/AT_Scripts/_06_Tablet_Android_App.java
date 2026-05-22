package AT_Scripts;


import Utils.DriverManager;
import Utils.ScreenShotLogic;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class _06_Tablet_Android_App {

    AndroidDriver driver;
    @Test(groups = {"All","Tablet","Android_Tablet","Android_Tablet_App"},
            dataProvider = "androidTabletAppCapsData",
            dataProviderClass = Supporting_Classes.DataProviders.DF_AT_App.class,
            invocationCount = 1)
    public void androidTabletAppGeneralStore(String dF_hub_url_Via_Xml, String model_Name, String platform_Name,
                                       String platform_Version, String app, String device_Type,
                                       Boolean is_Virtual, Boolean expect_Failure) throws Exception {

        boolean failureOccurred = false;

        try {
            try {
                String device_farm_hub_url = dF_hub_url_Via_Xml;
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("appium:deviceName", model_Name);
                caps.setCapability("platformName", platform_Name);
                caps.setCapability("appium:platformVersion", platform_Version);
                caps.setCapability("appium:app", app);
                caps.setCapability("appium:deviceType", device_Type);
                caps.setCapability("appium:isVirtual", is_Virtual);
                DriverManager.setDriver(new AndroidDriver(new URL(device_farm_hub_url), caps));
                driver = DriverManager.getDriver();

                System.out.println("Execution Started");
                ScreenShotLogic ss = new ScreenShotLogic(driver);


                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                Thread.sleep(3000);

                // 1. Select Country Dropdown
                driver.findElement(By.xpath("//android.widget.Spinner[@resource-id='com.androidsample.generalstore:id/spinnerCountry']")).click();
                ss.takeScreenshot("Step1_SelectCountryDropdown");

                // 2. Select Country
                Thread.sleep(5000);
                driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Afghanistan']")).click();
                Thread.sleep(5000);
                ss.takeScreenshot("Step1_SelectCountryDropdown");

                // 3. Enter Name
                WebElement nameField = driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']"));
                nameField.click();
                nameField.sendKeys("Tester1");
                ss.takeScreenshot("Step1_SelectCountryDropdown");
                driver.hideKeyboard();

                // 4. Select Gender
                driver.findElement(By.xpath("//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioMale']")).click();
                ss.takeScreenshot("Step1_SelectCountryDropdown");

                // 5. Click Let's Shop
                driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")).click();
                ss.takeScreenshot("Step5_ClickLetsShop");

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (DriverManager.getDriver() != null) {
                    DriverManager.getDriver().quit();
                    DriverManager.unload();
                } else {
                    System.out.println("Driver not initialized. Session creation failed.");
                }
            }

        } catch (Exception e) {
            failureOccurred = true;
            System.out.println("Failure occurred: " + e.getMessage());
        }


        if (expect_Failure && !failureOccurred) {
            Assert.fail("Expected failure but test PASSED");
        }

        if (!expect_Failure && failureOccurred) {
            Assert.fail("Test FAILED but was expected to PASS");
        }
    }
}