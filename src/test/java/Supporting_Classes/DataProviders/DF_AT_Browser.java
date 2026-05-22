package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_AT_Browser {
    @DataProvider
    public Object[][] androidTabletBrowserCapsData(org.testng.ITestContext context){
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }


        return new Object[][]{
                //{device_Name,platform_Name, platform_Version, browser_Name, device_Type, is_Virtual, expect_Failure
//              -------------------------------Real Devices-----------------------------------------
                {url, "Pixel Tablet","Android","16","Chrome","public",true, false}
        };
    }
}
