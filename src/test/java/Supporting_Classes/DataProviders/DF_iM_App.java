package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class DF_iM_App {
    public final String app = "bigbasket.ipa";
    public final String iOS_iPhone_App_bundle_ID = "com.bigbasket.mobileapp";
//    public final String app = "Meesho 2.84.1.ipa";
//    public final String bundle_ID = "com.meesho.Meesho";
    @DataProvider
    public Object[][] iOSMobileAppCapsData(org.testng.ITestContext context){
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }

        return new Object[][]{
                //{device_Name,platform_Name, platform_Version, app, device_Type, is_Virtual, bundle_ID, expect_Failure
                {url, "iPhone 11", "iOS" , "18.3", this.app , "public", false , this.iOS_iPhone_App_bundle_ID, false}
        };
    }
}
