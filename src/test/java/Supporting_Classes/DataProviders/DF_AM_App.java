package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_AM_App {
    public final String app = "General-Store-final (4).apk";
    @DataProvider
    public Object[][] androidMobileAppCapsData(org.testng.ITestContext context){
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }
        return new Object[][]{
//   Skeleton->{device_Name,platform_Name, platform_Version, app, device_Type, is_Virtual, expect_Failure

                {url, "Samsung Galaxy A12", "Android" , "12", this.app , "public", false , false},
                {url, "Pixel 7", "Android" , "15", this.app, "public", true , false},
                {url, "Pixel 7 Pro", "Android" , "14", this.app, "public", true , false},
//                {url, "Pixel 7 Pro", "Android" , "14", this.app, "public", false , true} //Validating that an exception is thrown when the isVirtual key is set to false for a virtual device.
        };
    }
}
