package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import timeutils.SleepUtils;

public class VideoPage extends BaseWebPage {

    public VideoPage() {
        waitUntilPageIsLoaded();
    }

    WebElement videoElement() {
        return driver.findElement(By.tagName("video"));
    }

    WebElement videoPlayButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-play-control vjs-control  vjs-playing']"));
    }

    public void clickOnVideo() {
        videoElement().click();
    }

    WebElement adUi() {
        return driver.findElement(By.xpath("//iframe[@title='Advertisement' and @src]"));
    }

    public boolean isAdPlaying() {
        if (!adUi().isDisplayed()) {
            return false;
        } else {
            return true;
        }
    }

    public WebElement adSkipButton() {
        return driver.findElement(By.className("videoAdUiSkipButton"));
    }

    public void verifyVideoPlaying() {
        if (videoPlayButton().isDisplayed()) {
            System.out.println("Video is playing...");
        } else {
            System.out.println("Video is paused...");
        }
    }

    public void handleAdPlayback() {
        SleepUtils.sleep(10);
        if (isAdPlaying()) {
            driver.switchTo().frame(adUi());
            waitUntilPageIsLoaded();
            if (adSkipButton().isDisplayed()) {
                adSkipButton().click();
                driver.switchTo().parentFrame();
            } else {
                driver.switchTo().parentFrame();
                waitForElementToDisappear(adUi(), 5);
            }
        }
    }

    WebElement nextVideoButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-control-bar']//div[contains(@class, 'mol-skip-control')]"));
    }

    WebElement prevVideoButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-control-bar']//div[contains(text(), 'Previous')]"));
    }

    WebElement muteVideoButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-volume-menu-button']//div[contains(text(), 'Mute')]"));
    }

    public void playNextVideo() {
        nextVideoButton().click();
    }

    public void playPrevVideo() {
        prevVideoButton().click();
    }

    public void clickSpeakerButton() {
        muteVideoButton().click();
    }

    public void finishCurrentVideo() {
        String script = "var video = document.querySelector('video'); if(video) video.currentTime = video.duration-1;";
        executeJavaScriptInWindow(script);
        clickOnVideo();
    }
}
