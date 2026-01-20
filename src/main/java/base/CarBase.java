package base;

import com.microsoft.playwright.Page;
import extentlisteners.ExtentListeners;
import org.testng.Assert;

public class CarBase {

    public Page page;

    public CarBase (Page page) {
        this.page = page;
    }

    public String getCarTitle() {
        String selector = BasePage.OR.getProperty("carTitle_CSS");
        String carTitle = page.locator(selector).innerText();
        try{
            ExtentListeners.testReport.get().info("Finding page title: " + carTitle + "(" + selector + ")");
            return carTitle;
        } catch (Throwable t){
            ExtentListeners.testReport.get().info("Error finding page title: " + carTitle + "(" + selector + "), " +
                    "error message is: " + t.getMessage());
            Assert.fail(t.getMessage());
            return null;
        }
    }
}
