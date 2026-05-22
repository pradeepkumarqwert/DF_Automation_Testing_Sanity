package AT_Scripts;

import Supporting_Classes.DataProviders.DF_Web_Caps;
import Utils.DriverManager;
import Utils.ScreenShotLogic;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

public class _01_Web_Automation
{
WebDriver driver;
    @Test(groups = {"All","Web"},
            dataProvider = "webBrowserCapsData",
            dataProviderClass = DF_Web_Caps.class,
            invocationCount = 1)
    public void webAutomationPantaloonsSite(String dF_hub_url_Via_Xml, String browserName,
                                            Boolean NetworkLog, String deviceType, String oSName,
                                            String browserVersion) throws Exception {

       try {


           String device_farm_hub_url = dF_hub_url_Via_Xml;
           MutableCapabilities options;

           switch (browserName.toLowerCase()) {
               case "chrome":
                   options = new ChromeOptions();
                   break;

               case "firefox":
                   options = new FirefoxOptions();
                   break;

               case "safari":
                   options = new SafariOptions();
                   break;

               case "edge":
                   options = new EdgeOptions();
                   break;
               case "comet":
                   options = new ChromeOptions();
                   break;

               default:
                   throw new RuntimeException("Invalid browser: " + browserName);
           }

           options.setCapability("devicefarm:networkLogEnable", NetworkLog);
           options.setCapability("fireflink:deviceType", deviceType);
           options.setCapability("platformName", oSName);
           options.setCapability("browserVersion", browserVersion);
           try {
               DriverManager.setDriver(new RemoteWebDriver(new URL(device_farm_hub_url), options));
               driver = DriverManager.getDriver();
           } catch (Exception e) {
               System.out.println("Driver initialization failed: " + e.getMessage());
               throw e;
           }
           driver.manage().window().setSize(new Dimension(1024, 768));

           ScreenShotLogic ss = new ScreenShotLogic((TakesScreenshot) driver);
           Actions act = new Actions(driver);


           //Fetch system info
           Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
           String printBrowserName = caps.getBrowserName();
           String printBrowserVersion = caps.getBrowserVersion();
           Platform printPlatform = caps.getPlatformName();
           String printSessionId = ((RemoteWebDriver) driver).getSessionId().toString();


           System.out.println("Browser: " + printBrowserName);
           System.out.println("Version: " + printBrowserVersion);
           System.out.println("Platform: " + printPlatform);
           System.out.println("Session ID is: " + printSessionId);


           driver.get("https://www.google.com/");
           driver.manage().window().maximize();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
           ss.takeScreenshot("04_After_Search_Result_Click");
           driver.navigate().to("https://pantaloons.com");
           ss.takeScreenshot("04_After_Search_Result_Click");
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
           wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState").equals("complete"));
           ss.takeScreenshot("04_After_Search_Result_Click");
//        //search product
//        driver.findElement(By.xpath("//div[@class='nav-links']//input[@placeholder='Search']")).click();
//        driver.findElement(By.xpath("//div[@class='nav-links']//input[@placeholder='Search']")).sendKeys("Shirts");
//        ss.takeScreenshot("04_After_Search_Result_Click");
//        driver.findElement(By.xpath("//div[@class='nav-links']//input[@placeholder='Search']")).sendKeys(Keys.ENTER);
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'MuiGrid-grid-lg-3 PlpWeb_filter-grid__AbSIK')]//div[contains(@class,'PlpWeb_filter-container__gkVa2')]//p[text()='Gender']")));
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-grid-lg-3 PlpWeb_filter-grid__AbSIK')]//div[contains(@class,'PlpWeb_filter-container__gkVa2')]//p[text()='Gender']"))));
//        ss.takeScreenshot("04_After_Search_Result_Click");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript(
//                "arguments[0].scrollIntoView({block: 'center'});",
//                driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-grid-lg-3 PlpWeb_filter-grid__AbSIK')]//div[contains(@class,'PlpWeb_filter-container__gkVa2')]//p[text()='Gender']"))
//        );
//        act.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-grid-lg-3 PlpWeb_filter-grid__AbSIK')]//div[contains(@class,'PlpWeb_filter-container__gkVa2')]//p[text()='Gender']")));
//        ss.takeScreenshot("04_After_Search_Result_Click");
//        driver.findElement(By.xpath("//div[contains(@class,'MuiGrid-grid-lg-3 PlpWeb_filter-grid__AbSIK')]//div[contains(@class,'PlpWeb_filter-container__gkVa2')]//p[text()='Gender']")).click();
//        ss.takeScreenshot("04_After_Search_Result_Click");
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Boys']//ancestor::div[contains(@class,'PlpWeb_filter-values')]//input"))).click();
//        ss.takeScreenshot("04_After_Search_Result_Click");

       } catch (Exception e) {
           throw new RuntimeException(e);
       }finally {
           if (DriverManager.getDriver() != null) {
               DriverManager.getDriver().quit();
               DriverManager.unload();
           } else {
               System.out.println("Driver not initialized. Session creation failed.");
           }
       }

    }


}
