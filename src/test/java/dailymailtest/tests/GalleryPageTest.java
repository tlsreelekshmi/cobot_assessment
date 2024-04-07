package dailymailtest.tests;

import dailymailtest.BaseTest;
import org.testng.annotations.Test;
import webpages.GalleryPage;
import webpages.HomePage;
import webpages.NewsPage;

public class GalleryPageTest extends BaseTest {

    @Test
    public void viewGalleryFromHomeArticlePageTest() {
        new HomePage().homeArticlePageElement().click();
        new NewsPage().clickGalleryButtonInNewsPage();
        GalleryPage galleryPage = new GalleryPage();
        galleryPage.clickRightArrowTillEnd();
        galleryPage.closeGallery();
    }
}