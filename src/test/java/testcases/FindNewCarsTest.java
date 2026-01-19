package testcases;

import base.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewCarsPage;
import utilities.Constants;
import utilities.DataProviders;
import utilities.DataUtil;
import utilities.ExcelReader;

import java.util.Hashtable;

public class FindNewCarsTest extends BaseTest {

    @Test(dataProviderClass = DataProviders.class, dataProvider = "carwaleDP")
    public void findNewCarsTest(Hashtable<String, String> data) {
        ExcelReader excel = new ExcelReader(Constants.SUITE3_XL_PATH);
        DataUtil.checkExecution("CarWaleSuite", "FindNewCarsTest", data.get("Runmode"), excel);

        Browser browser = getBrowser(data.get("browser"));
        navigate(browser, Constants.URL);


        Page page = getPage();
        HomePage home = new HomePage(page);
        home.findNewCars();
        NewCarsPage cars = new NewCarsPage(page);

        switch(data.get("carbrand")){
          case "kia" -> cars.gotoKia();
          case "bmw" -> cars.gotoBMW();
          case "honda" -> cars.gotoHonda();
          case "toyota" -> cars.gotoToyota();
        };

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
