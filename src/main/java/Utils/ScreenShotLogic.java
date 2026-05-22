package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ScreenShotLogic {

    private TakesScreenshot driver;

    public ScreenShotLogic(TakesScreenshot driver) {
        this.driver = driver;
    }

    public void takeScreenshot(String fileName) {
        try {
            File src = driver.getScreenshotAs(OutputType.FILE); // ✅ no cast needed
            File dest = new File("C:\\Selenium Grid\\Screenshots\\" + fileName + ".png");
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Screenshot Failed: " + e.getMessage());
        }
    }
}