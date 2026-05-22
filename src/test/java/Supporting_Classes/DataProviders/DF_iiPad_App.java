package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_iiPad_App {
    public final String app = "iOS.Simulator.SauceLabs.Mobile.Sample.app.zip";
    public final String iOS_iPad_App_bundle_Id = "com.saucelabs.SwagLabsMobileApp";

    @DataProvider
    public Object[][] iOSiPadAppCapsData(org.testng.ITestContext context) { String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }

        return new Object[][]{
//      Skeleton->{device_Name,platform_Name, platform_Version, app, device_Type, is_Virtual, expect_Failure
//        ---------------------------------------Tablet Real Devices-------------------------------
                {url, "Simulator iPad Air 13inch M3", "iOS", "18.4", this.app, "public", true, this.iOS_iPad_App_bundle_Id , false},
//                {url, "Simulator iPad Air 13inch M3", "iOS", "18.4", this.app, "public", false, this.iOS_iPad_App_bundle_Id , true}

        };
    }
}
