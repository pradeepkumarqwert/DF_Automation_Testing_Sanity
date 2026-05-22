package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_AT_App {
    public final String app = "General-Store-final (4).apk";

    @DataProvider
    public Object[][] androidTabletAppCapsData(org.testng.ITestContext context) {
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }


        return new Object[][]{
//      Skeleton->{device_Name,platform_Name, platform_Version, app, device_Type, is_Virtual, expect_Failure
//        ---------------------------------------Tablet Real Devices-------------------------------
                {url, "Pixel Tablet", "Android", "16", this.app, "public", false, false},
//                {url, "Pixel Tablet", "Android", "16", this.app, "public", true, true}


        };
    }
}
