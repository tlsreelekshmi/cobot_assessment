package dailymailtest.tests;

import dailymailtest.BaseTest;
import org.testng.annotations.Test;
import timeutils.SleepUtils;
import webpages.VideoPage;

public class VideoPlayerTest extends BaseTest {

    @Test
    public void videoPlayerUiTest() {
        openUrl("https://www.dailymail.co.uk/video/index.html");
        VideoPage videoPage = new VideoPage();
        videoPage.clickOnVideo();
        SleepUtils.sleep(5); //buffer time
        videoPage.handleAdPlayback();
        videoPage.verifyVideoPlaying(); //Ensure video is playing
        videoPage.clickOnVideo(); //Paused ad playback
        videoPage.playNextVideo();
        videoPage.handleAdPlayback();
        SleepUtils.sleep(5);
        videoPage.playPrevVideo();
        SleepUtils.sleep(5);
        videoPage.clickMuteButton(); //Mute video
        SleepUtils.sleep(2);
        videoPage.clickUnMuteButton(); //Un Mute video
        videoPage.finishCurrentVideo();
    }
}
