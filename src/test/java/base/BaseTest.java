package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import extentlisteners.ExtentListeners;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;

public class BaseTest {

    private Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;

    private final Logger log = LogManager.getLogger(this.getClass());

    private static ThreadLocal<Playwright> pw = new ThreadLocal<>();
    private static ThreadLocal<Browser> br = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> ctx = new ThreadLocal<>();
    private static ThreadLocal<Page> pg = new ThreadLocal<>();

    public static Playwright getPlaywright() {
        return pw.get();
    }

    public static Browser getBrowser() {
        return br.get();
    }

    public static BrowserContext getBrowserContext() {
        return ctx.get();
    }

    public static Page getPage() {
        return pg.get();
    }

    @BeforeSuite
    public void setUp() {
        Configurator.initialize(null, "./src/test/resources/log4j2.properties");
        log.info("--- New Test Suite Execution Started ---");
    }

    public Browser getBrowser(String browserName){
        try {
            playwright = Playwright.create();
            pw.set(playwright);
            ArrayList<String> arguments = new ArrayList<>();
            arguments.add("--start-maximized");

            browser = switch (browserName) {
                case "chrome" -> {
                    log.info("Launching Chrome Browser");
                    yield getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)
                            .setArgs(arguments));
                }
                case "firefox" -> {
                    log.info("Launching Firefox Browser");
                    yield getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false)
                            .setArgs(arguments));
                }
                case "webkit" -> {
                    log.info("Launching Webkit Browser");
                    yield getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setChannel("webkit")
                            .setArgs(arguments));
                }
                case "headless" -> {
                    log.info("Launching Headless Browser");
                    yield getPlaywright().chromium().launch(new BrowserType.LaunchOptions()
                            .setArgs(arguments));
                }
                default -> {
                    log.info("Invalid browser name");
                    throw new IllegalArgumentException("Invalid browser name");
                }
            };

            return browser;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigate(Browser browser, String url){
        this.browser = browser;
        br.set(browser);
        browserContext = getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null));
        ctx.set(browserContext);
        page = getBrowserContext().newPage();
        pg.set(page);
        getPage().navigate(url);
        log.info("Navigated to: " + url);

        getPage().onDialog(dialog -> {
            dialog.accept();
            System.out.println(dialog.message());
        });
    }

    @AfterMethod
    public void quit() {
        if (getPage() != null) {
            getPage().close();
        }
        if (getBrowser() != null) {
            getBrowser().close();
        }
    }

    @AfterSuite
    public void quit2() {
        if (getPlaywright() != null) {
            playwright.close();
        }
    }
}