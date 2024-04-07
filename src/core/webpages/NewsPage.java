package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NewsPage extends BaseWebPage {

    public NewsPage() {
        waitUntilPageIsLoaded();
    }

    public WebElement articleImage() {
        return driver.findElement(By.className("artSplitter"));
    }

    public WebElement viewGalleryButton() {
        return driver.findElement(By.xpath("//button[contains(@class,'openGalleryButton')]"));
    }

    public void clickGalleryButtonInNewsPage() {
        scrollToElement(articleImage());
        clickElementWithJs(viewGalleryButton());
    }
}
