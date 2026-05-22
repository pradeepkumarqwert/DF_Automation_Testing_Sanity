package Runner;

import org.testng.TestNG;
import java.util.List;

public class TestRunner_CrossBrowser {
    public static void main(String[] args) {

        TestNG testng = new TestNG();
        testng.setTestSuites(List.of("D:\\DF_AT_Selenium\\DF_Automation_Testing_Selenium\\testng.xml"));
        testng.setUseDefaultListeners(true);
        testng.run();
    }
}