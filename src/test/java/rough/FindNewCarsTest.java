package rough;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import pages.HomePage;
import pages.NewCarsPage;

public class FindNewCarsTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false))) {
                Page page = browser.newPage();

                page.navigate("https://www.carwale.com/");

                HomePage home = new HomePage(page);
                home.findNewCars();
                NewCarsPage cars = new NewCarsPage(page);
                cars.gotoBMW();

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
