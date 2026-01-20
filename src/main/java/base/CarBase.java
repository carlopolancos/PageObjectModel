package base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import extentlisteners.ExtentListeners;
import org.testng.Assert;

import java.util.List;

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

    public void getCarNameAndPrice() {
        Locator carNames = page.locator(BasePage.OR.getProperty("carName_XPATH"));
        Locator carPrices = page.locator(BasePage.OR.getProperty("carPrice_XPATH"));

        for (int i =0; i < carPrices.count(); i++) {
            System.out.println(carNames.nth(i).innerText() + " is priced at " + carPrices.nth(i).innerText());
        }
    }
}
