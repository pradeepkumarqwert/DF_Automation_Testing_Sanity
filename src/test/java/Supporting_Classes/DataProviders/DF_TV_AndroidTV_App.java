package Supporting_Classes.DataProviders;

import com.sun.jna.WString;
import org.testng.annotations.DataProvider;

public class DF_TV_AndroidTV_App {
    public final String TV_App = "ApiDemos-debug.apk";

    @DataProvider
    public Object[][] TVAndroidTvAppData(org.testng.ITestContext context){
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if(url == null || url.isEmpty()){
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }
        return new Object[][]{
//           {Hub_URL, device_Name, platform_Name, platform_Version, App, device_Type, is_Virtual, expect_Failure(Wants to test outcome to be fail?)
                {url, "Google TV", "Android", "16", this.TV_App, "public", true, false}
        };
    }
}
