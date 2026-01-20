package base;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import extentlisteners.ExtentListeners;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BasePage {

    public static Page page;
    public static ThreadLocal<Page> pg = new ThreadLocal<>();
    public static Properties OR = new Properties();
    private static FileInputStream fis;
    public static CarBase carBase;

    public static Page getPage() { return pg.get(); }

    public BasePage(Page page){
        BasePage.page = page;
        carBase = new CarBase(page);
        pg.set(BasePage.page);
        try {
            fis = new FileInputStream("./src/main/resources/properties/OR.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }

        try {
            OR.load(fis);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void click(String locatorKey) {
        String selector = OR.getProperty(locatorKey);
        try {
            page.locator(selector).click();
            ExtentListeners.testReport.get().info("Clicking on element " + locatorKey + "(" + selector + ")");
        } catch (Throwable t) {
            ExtentListeners.testReport.get().fail("Error while clicking on an element " + locatorKey + "(" + selector + "): " + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    public void hover(String locatorKey) {
        String selector = OR.getProperty(locatorKey);
        try {
            page.locator(selector).hover(new Locator.HoverOptions().setTimeout(30000));
            ExtentListeners.testReport.get().info("Hovering on element " + locatorKey + "(" + selector + ")");
        } catch (Throwable t) {
            ExtentListeners.testReport.get().fail("Error while hovering on an element " + locatorKey + "(" + selector + "): " + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    public void fill(String locatorKey, String value) {
        String selector = OR.getProperty(locatorKey);
        try {
            page.locator(selector).fill(value);
            ExtentListeners.testReport.get().info("Typing on element " + locatorKey + "(" + selector + ") and entered the value: " + value);
        } catch (Throwable t) {
            ExtentListeners.testReport.get().fail("Error while filling \"" + value + "\" on element " + locatorKey + "(" + selector + "): " + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    public void select(String locatorKey, String value) {
        String selector = OR.getProperty(locatorKey);
        try {
            page.locator(selector).selectOption(new SelectOption().setLabel(value));
            ExtentListeners.testReport.get().info("Selecting on element " + locatorKey + "(" + selector + ") and selected the value: " + value);
        } catch (Throwable t) {
            ExtentListeners.testReport.get().fail("Error while selecting \"" + value + "\" on element " + locatorKey + "(" + selector + "): " + t.getMessage());
            Assert.fail(t.getMessage());
        }
    }

    public boolean isElementPresent(String locatorKey) {
        String selector = OR.getProperty(locatorKey);
        Locator element = page.locator(selector);
        try {
            page.waitForSelector(selector, new Page.WaitForSelectorOptions().setTimeout(5000));
            if (element.isVisible()) {
                ExtentListeners.testReport.get().info("Finding element " + locatorKey + "(" + selector + ")");
                return true;
            } else {
                ExtentListeners.testReport.get().info("Finding element " + locatorKey + "(" + selector + ")");
                return false;
            }
        } catch (Throwable t) {
            ExtentListeners.testReport.get().fail("Error while finding element " + locatorKey + "(" + OR.getProperty(locatorKey) + "): " + t.getMessage());
            return false;
        }
    }

}
