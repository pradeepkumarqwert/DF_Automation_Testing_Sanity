package Supporting_Classes.DataProviders;

import org.openqa.selenium.devtools.v85.layertree.model.StickyPositionConstraint;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_AM_Browser {
    @DataProvider
    public Object[][] androidMobileBrowserCapsData(org.testng.ITestContext context){
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }

        return new Object[][]{
              //{device_Name,platform_Name, platform_Version, browser_Name, device_Type, is_Virtual, expect_Failure

//              ----------------------------Virtual Devices-----------------------------------------
                {url, "Pixel 7 Pro","Android","14","Chrome","public",true, false},
                {url, "Pixel 7","Android","15","Chrome","public",true, false},
//              -------------------------------Real Devices-----------------------------------------
                {url, "OnePlus Nord CE 2 Lite 5G","Android","14","Chrome","public",false, false},
                {url, "Samsung Galaxy A12","Android","12","Chrome","public",false, false},
//              ----------------------------Expected to fail because of wrong isVirtual Key value-----------------------------------------
//                {url, "Pixel 7 Pro","Android","14","Chrome","public",false, true}


        };
    }
}
