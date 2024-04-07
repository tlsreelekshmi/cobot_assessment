package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import timeutils.SleepUtils;


public class GalleryPage extends BaseWebPage {

    public GalleryPage() {
        waitUntilPageIsLoaded();
    }

    public WebElement rightArrowButton() {
        return driver.findElement(By.xpath("//button[@aria-label='Next']"));
    }

    public WebElement shareThisGalleryButton() {
        return driver.findElement(By.xpath("//div[contains(text(), 'Share this gallery')]"));
    }

    public WebElement galleryCloseIcon() {
        return driver.findElement(By.xpath("//button[@aria-label='Close']"));
    }

    public void clickRightArrowTillEnd() {
        while (!shareThisGalleryButton().isDisplayed()) {
            rightArrowButton().click();
            SleepUtils.sleep(2);
        }
    }

    public void closeGallery() {
        galleryCloseIcon().click();
        SleepUtils.sleep(2);
    }
}
