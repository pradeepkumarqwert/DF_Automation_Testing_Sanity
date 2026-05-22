package Supporting_Classes.DataProviders;

import org.testng.annotations.DataProvider;


public class DF_Web_Caps {
    @DataProvider
    public Object[][] webBrowserCapsData(org.testng.ITestContext context){
        String url = context.getCurrentXmlTest().getParameter("dF_hub_url_Via_Xml");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("URL parameter 'dF_hub_url_Via_Xml' is missing in testng.xml");
        }
        
        return new Object[][]{

                {url, "chrome", true, "public", "Windows 11", "136"},
                {url, "firefox", true, "public", "Windows 11", "136"},
                {url, "edge", true, "public", "Windows 11", "136"},

                {url, "chrome", true, "public", "Windows 10", "136"},
                {url, "firefox", true, "public", "Windows 10", "136"},
                {url, "edge", true, "public", "Windows 10", "136"},
                {url, "comet",true,"public","Windows 10","142.0"},

                {url, "chrome", true, "public", "mac Tahoe", "136"},
                {url, "firefox", true, "public", "mac Tahoe", "136"},
                {url, "safari", true, "public", "mac Tahoe", "136"},
                {url, "comet",true,"public","mac Tahoe","142.0"},

                {url, "chrome", true, "public", "linux", "136"},
                {url, "firefox", true, "public", "linux", "136"},


        };
    }
}
