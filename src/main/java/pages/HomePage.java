package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage extends BasePage {

    public HomePage(Page page) {
        super(page);
    }

    public void findNewCars() {
        hover("newCarsMenu_CSS");
        click("findNewCars_CSS");
    }

    public void searchCars() {

    }

    public void validateFeaturedCars() {

    }


}
