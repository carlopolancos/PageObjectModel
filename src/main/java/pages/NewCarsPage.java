package pages;

import base.BasePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NewCarsPage extends BasePage {

    public NewCarsPage(Page page) {
        super(page);
    }

    public void gotoHonda(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Honda Cars Honda")).click();
    }

    public void gotoToyota(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Toyota Cars Toyota")).click();
    }

    public void gotoKia(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Kia Cars Kia")).click();
    }

    public void gotoBMW(){
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("BMW Cars BMW")).click();
    }
}
