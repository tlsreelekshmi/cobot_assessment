package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BaseWebPage {

    public HomePage() {
        waitUntilPageIsLoaded();
    }

    public WebElement homeArticlePageElement() {
        return driver.findElement(By.xpath("//a[contains(@href, '/news/article')]"));
    }
}
