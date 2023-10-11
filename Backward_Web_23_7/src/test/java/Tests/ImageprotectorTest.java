package Tests;

import Data.LoadProperties;
import Pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static Tests.EncryptionToolsTest.passwordQA;
import static Tests.EncryptionToolsTest.usernameQA;

public class ImageprotectorTest extends TestBase {
    static Boolean mode;
    static HomePage homeobject;
    static LoginPage loginobject;
    static WebDriverWait wait;
    static EncryptionToolsPage encryptionToolsPage;
    static ImageprotectorPage imageprotectorPage;
    static  PDFProtectorPage pdfProtectorPage;
    static  PageBase page;
    String txtORIGIN =LoadProperties.testData.getProperty("TxtpathORIGN");
    String txtDecrypt = LoadProperties.testData.getProperty("savedDecryptedTXT");
    String imageOrigin = LoadProperties.testData.getProperty("ImagePATH");
    String encryptedImage = LoadProperties.testData.getProperty("savedEncryptedIMG");
    String encryptedSelectionimg = LoadProperties.testData.getProperty("savedEncryptedSelectionIMG");
    String DecryptedImage = LoadProperties.testData.getProperty("savedDecryptedIMG");
    String DecryptedSelectionimg = LoadProperties.testData.getProperty("savedDecryptedSelectionIMG");
     static String tracerID;
     static String tracerIDselection;
    public ImageprotectorTest() throws AWTException {
    }

    @Test(priority = 1)
    public void successful_Whole_encryption_image() throws AWTException, InterruptedException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        imageprotectorPage = new ImageprotectorPage(driver);
        imageprotectorPage.imagebtn.click();
        imageprotectorPage.encryptImage_PDF(imageOrigin,driver);
        imageprotectorPage.saveFile_image.click();
        page.save_files(encryptedImage);
        Assert.assertTrue(imageprotectorPage.viewencryptedBTN.isEnabled());
        WebElement tracerid = imageprotectorPage.tracerDetails.get(0);
        tracerID= tracerid.getText();
        System.out.print("this is tracer for full encryption ");
        System.out.print(tracerID);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.txtAfterfullencryption.isDisplayed());
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
    }
    @Test(priority = 2,dependsOnMethods ="successful_Whole_encryption_image")
    public void successful_selection_encryption_image_owner_nokey() throws InterruptedException, AWTException {
        driver.navigate().refresh();
        imageprotectorPage.EncryptImage_Selection(imageOrigin, driver);
        imageprotectorPage.saveFile_image.click();
        page.save_files(encryptedSelectionimg);
        Assert.assertTrue(imageprotectorPage.viewencryptedBTN.isEnabled());
        Assert.assertFalse(imageprotectorPage.encryptDisabledbtn.isEnabled());
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertFalse(imageprotectorPage.removeSelectedbtn.isEnabled());
        imageprotectorPage.editBtn2.click();
        WebElement tracerid = imageprotectorPage.tracerDetails.get(0);
        tracerIDselection = tracerid.getText();
        System.out.print(" this is tracer for selection ");
        System.out.print(tracerIDselection);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
    }
    @Test(priority = 3, dependsOnMethods = "successful_Whole_encryption_image")
    public void successful_FullDecyrption_ViewEncryptedContent() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        imageprotectorPage.choosefilebtn.sendKeys(encryptedImage);
        imageprotectorPage.viewencryptedBTN.click();
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        System.out.print(" after view encrypted full decryption tracer ");
        System.out.print(tracerID);
        Assert.assertEquals(imageprotectorPage.tracerDetails.get(0).getText(), tracerID);
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(imageprotectorPage.txtAfterfullencryption.isDisplayed());
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
        imageprotectorPage.reEncryptbtn.click();
    }
    @Test(priority = 4, dependsOnMethods = {"successful_Whole_encryption_image", "successful_FullDecyrption_ViewEncryptedContent"})
    public void successful_FullDecyrption_removeAll() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        imageprotectorPage.choosefilebtn.sendKeys(encryptedImage);
        Thread.sleep(2000);
        imageprotectorPage.editBtn2.click();
        imageprotectorPage.removeallencryptionBTN.click();
        Assert.assertTrue(imageprotectorPage.removeCONFIDENCIAL.isEnabled());
        Assert.assertTrue(imageprotectorPage.encryptWholeBtn.isEnabled());
        imageprotectorPage.editBtn2.click();
        System.out.print(" after remove all decryotion full tracer id ");
        System.out.print(tracerID);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        Assert.assertEquals(imageprotectorPage.tracerDetails.get(0).getText(), tracerID);
    }
    @Test(priority = 5,dependsOnMethods = "successful_Whole_encryption_image")
    public void successful_whole_decryption_image_owner_nokey() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        imageprotectorPage.decryptIMG_PDF(encryptedImage,driver ,true);
        imageprotectorPage.saveFile_image.click();
        page.save_files(DecryptedImage);
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.textAfterdecryption.isDisplayed());
        boolean b = page.CompareFiles_Hashfn(imageOrigin,DecryptedImage);
        //Assert.assertTrue(b);
    }

    @Test(priority = 6, dependsOnMethods ={ "successful_Whole_encryption_image", "successful_selection_encryption_image_owner_nokey"})
    public void successful_selection_decryption_image_owner_nokey() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        imageprotectorPage.decryptIMG_PDF(encryptedSelectionimg,driver ,false);
        imageprotectorPage.saveFile_image.click();
        page.save_files(DecryptedSelectionimg);
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.textAfterdecryption.isDisplayed());
        boolean b = page.CompareFiles_Hashfn(imageOrigin,DecryptedSelectionimg);
       // Assert.assertTrue(b);
    }
    @Test(priority = 7 , dependsOnMethods = {"successful_Whole_encryption_image", "successful_selection_encryption_image_owner_nokey"})
    public void successful_selectionecyrption_ViewEncryptedContent() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        imageprotectorPage.choosefilebtn.sendKeys(encryptedSelectionimg);
        imageprotectorPage.viewencryptedBTN.click();
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        System.out.print(" after view encrypted selection decryption tracer ");
        System.out.print(tracerIDselection);
        Assert.assertEquals(imageprotectorPage.tracerDetails.get(0).getText(), tracerIDselection);
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.reEncryptbtn.isDisplayed());
        Assert.assertFalse(imageprotectorPage.encryptDisabledbtn.isEnabled());
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertFalse(imageprotectorPage.removeSelectedbtn.isEnabled());
        imageprotectorPage.reEncryptbtn.click();
    }
    @Test(priority = 8, dependsOnMethods = {"successful_Whole_encryption_image", "successful_FullDecyrption_ViewEncryptedContent"})
    public void successful_selectionDecyrption_removeAll() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        imageprotectorPage.choosefilebtn.sendKeys(encryptedSelectionimg);
        Thread.sleep(2000);
        imageprotectorPage.removeallencryptionBTN.click();
        Assert.assertTrue(imageprotectorPage.removeCONFIDENCIAL.isEnabled());
        Assert.assertTrue(imageprotectorPage.encryptWholeBtn.isEnabled());
        imageprotectorPage.editBtn2.click();
        System.out.print(" after remove all decryotion selection tracer id ");
        System.out.print(tracerIDselection);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        Assert.assertEquals(imageprotectorPage.tracerDetails.get(0).getText(), tracerIDselection);
    }



/*    @Test
    public void test_hash() throws IOException, AWTException {
        page = new PageBase(driver);
        boolean b= page.CompareFiles_Hashfn(imageOrigin,DecryptedImage);
        System.out.print(b);
        boolean s = page.CompareFiles_Hashfn(txtORIGIN, txtDecrypt);
        System.out.print(s);
    }*/
}
