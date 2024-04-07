package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BaseWebPage {

    protected static WebDriver driver;

    BaseWebPage() {
    }

    public BaseWebPage(WebDriver driver) {
        BaseWebPage.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        try {
            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
            System.out.println(this.getClass().getSimpleName() + " page is loaded!!!");
        } catch (TimeoutException e) {
            System.out.println(this.getClass().getSimpleName() + " is still loading!!!");
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickElementWithJs(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        // Execute the JavaScript code to click the element
        try {
            executor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForElementToDisappear(WebElement element, int timeoutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeoutInSec* 1000L));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void executeJavaScriptInWindow(String script) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }
}
