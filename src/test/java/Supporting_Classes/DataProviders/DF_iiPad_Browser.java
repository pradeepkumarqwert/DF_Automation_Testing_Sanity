package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_iiPad_Browser {
    @DataProvider
    public Object[][] iOSiPadBrowserCapsData(org.testng.ITestContext context) {
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }


        return new Object[][]{
                //{device_Name,platform_Name, platform_Version, browser_Name, device_Type, is_Virtual, expect_Failure
//              -------------------------------Virtual Devices-----------------------------------------
                {url, "Simulator iPad Air 13inch M3", "iOS", "18.4", "Safari", "public", true, false},
//                {url, "Simulator iPad Air 13inch M3", "iOS", "18.4", "Safari", "public", false, true},

        };
    }
}
