package dailymailtest;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import webpages.BaseWebPage;
import webpages.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static WebDriver driver;
    protected static Properties ConfigProps;


    @BeforeMethod
    public void initTest() {
        String browser = ConfigProps.getProperty("BROWSER");
        System.out.println("Starting browser " + browser);
        initBrowser(browser);
        openUrl();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        new BaseWebPage(driver).waitUntilPageIsLoaded();
    }

    public void openUrl() {
        String url = "https://www.dailymail.co.uk";
        System.out.printf("Launching URL %s... %n", url);
        driver.get(url);
    }

    public void openUrl(String url) {
        System.out.printf("Navigates to %s ... %n", url);
        driver.navigate().to(url);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    public void initBrowser(String browser) {
        driver = switch (browser) {
            case "CHROME" -> new ChromeDriver();
            case "SAFARI" -> new SafariDriver();
            case "EDGE" -> new EdgeDriver();
            case "FIREFOX" -> new FirefoxDriver();
            default -> {
                try {
                    throw new Exception("Invalid browser Name");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    static {
        ConfigProps = new Properties();
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            ConfigProps.load(fs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
