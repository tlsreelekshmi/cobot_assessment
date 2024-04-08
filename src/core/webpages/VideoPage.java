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

    WebElement videoPlayPauseButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-control-bar']//div[contains(@class, 'vjs-play-control')]"));
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
        if (isVideoPlaying()) {
            System.out.println("Video is playing...");
        } else {
            System.out.println("Video is paused...");
            videoPlayPauseButton().click();
            System.out.println("Video is playing...");
        }
    }

    public void handleAdPlayback() {
        SleepUtils.sleep(10);
        if (isAdPlaying()) {
            System.out.println("Ad is playing..");
            driver.switchTo().frame(adUi());
            System.out.println("Switched to ad iframe..");
            waitUntilPageIsLoaded();
            if (adSkipButton().isDisplayed()) {
                adSkipButton().click();
                System.out.println("Clicked on ad skip button..");
                driver.switchTo().parentFrame();
            } else {
                System.out.println("Ad skip button not found. waiting for ad to finish..");
                driver.switchTo().parentFrame();
                waitForElementToDisappear(adUi(), 5);
            }
        }
        SleepUtils.sleep(5);
    }

    WebElement nextVideoButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-control-bar']//div[contains(@class, 'mol-skip-control')]"));
    }

    WebElement prevVideoButton() {
        return driver.findElement(By.xpath("//div[@class='vjs-control-bar']//span[text()='Previous']"));
    }

    WebElement muteVideoButton() {
        return driver.findElement(By.xpath("//div[contains(@class,'vjs-volume-menu-button')]//span[contains(text(), 'Mute')]"));
    }

    WebElement unMuteVideoButton() {
        return driver.findElement(By.xpath("//div[contains(@class, 'vjs-volume-menu-button') and @aria-pressed]//span[text()='Unmute']"));
    }

    public void playNextVideo() {
        System.out.println("Playing next video..");
        nextVideoButton().click();
    }

    public void playPrevVideo() {
        System.out.println("Playing previous video..");
        clickElementWithJs(prevVideoButton());
    }

    public void clickMuteButton() {
        System.out.println("Click on mute button");
        clickElementWithJs(muteVideoButton());
    }

    public void clickUnMuteButton() {
        System.out.println("Click on un mute button");
        clickElementWithJs(unMuteVideoButton());
    }

    public void finishCurrentVideo() {
        String script = "var video = document.querySelector('video'); if(video) video.currentTime = video.duration-1;";
        executeJavaScriptInWindow(script);
        if (!isVideoPlaying()) {
            videoPlayPauseButton().click();
        }
    }

    public boolean isVideoPlaying() {
        String videoState = videoPlayPauseButton().getText();
        return videoState.equalsIgnoreCase("Pause");
    }
}
