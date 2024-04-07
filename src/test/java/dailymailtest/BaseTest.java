package dailymailtest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;

    @BeforeMethod
    public void initTest() {
        driver = new ChromeDriver();
        driver.get("https://www.dailymail.co.uk");
        driver.manage().window().maximize();
        waitUntilPageIsLoaded();
    }

    @AfterMethod
    public void teardown() {
        //take screenshot if failed
        driver.quit();
    }

    public void waitUntilPageIsLoaded() {
        //move to base web page class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
