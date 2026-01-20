package base;

<<<<<<< HEAD
import com.microsoft.playwright.Locator;
=======
>>>>>>> 1902031813febc7fc87529f0d8c6c96be9e5d878
import com.microsoft.playwright.Page;
import extentlisteners.ExtentListeners;
import org.testng.Assert;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> 1902031813febc7fc87529f0d8c6c96be9e5d878
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
<<<<<<< HEAD

    public void getCarNameAndPrice() {
        Locator carNames = page.locator(BasePage.OR.getProperty("carName_XPATH"));
        Locator carPrices = page.locator(BasePage.OR.getProperty("carPrice_XPATH"));

        for (int i =0; i < carPrices.count(); i++) {
            System.out.println(carNames.nth(i).innerText() + " is priced at " + carPrices.nth(i).innerText());
        }
    }
=======
>>>>>>> 1902031813febc7fc87529f0d8c6c96be9e5d878
}
