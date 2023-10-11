package Tests;

import Data.LoadProperties;
import Pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class ImageProtectorWithLocalkeyTest extends TestBase{
    public ImageProtectorWithLocalkeyTest() throws AWTException {
    }
    static Boolean mode;
    static HomePage homeobject;
    static LoginPage loginobject;
    static WebDriverWait wait;
    static EncryptionToolsPage encryptionToolsPage;
    static ImageprotectorPage imageprotectorPage;
    static PDFProtectorPage pdfProtectorPage;
    static  PageBase page;
    String imageOrigin = LoadProperties.testData.getProperty("Imagepath2");
    String encryptedImage = LoadProperties.testData.getProperty("savedEncryptedIMG2");
    String encryptedSelectionimg = LoadProperties.testData.getProperty("savedEncryptedSelectionIMG2");
    String DecryptedImage = LoadProperties.testData.getProperty("savedDecryptedIMG2");
    String DecryptedSelectionimg = LoadProperties.testData.getProperty("savedDecryptedSelectionIMG2");
    public  static String usernameQALocalkey = LoadProperties.testData.getProperty("usernameQAlocalkey2");
    public  static String passwordQALocalkey = LoadProperties.testData.getProperty("passwordQALocalKey2");
    public static String Localkeyowner = LoadProperties.testData.getProperty("localKeyOwner2");
    static String tracerIDlocalKey;
    static String tracerIDselectionLocalKey;


    @Test
    public void successful_Whole_encryption_image_withLocalKey() throws AWTException, InterruptedException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        loginobject.LOGIN(usernameQALocalkey, passwordQALocalkey);
        imageprotectorPage = new ImageprotectorPage(driver);
        imageprotectorPage.imagebtn.click();
        imageprotectorPage.encryptImage_PDF(imageOrigin,driver);
        imageprotectorPage.saveFile_image.click();
        Thread.sleep(5000);
        page.save_files(encryptedImage);
        Assert.assertTrue(imageprotectorPage.viewencryptedBTN.isEnabled());
        WebElement tracerid = imageprotectorPage.tracerDetails.get(0);
        tracerIDlocalKey = tracerid.getText();
        System.out.print("this is tracer for full encryption ");
        System.out.print(tracerIDlocalKey);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        imageprotectorPage.editBtn2.click();
        Assert.assertEquals(imageprotectorPage.txtAfterfullencryption.getText() , "This Image is fully encrypted and cannot be modified. To edit the Image, the encryption must first be removed.");
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
    }
    @Test(priority = 2,dependsOnMethods ="successful_Whole_encryption_image_withLocalKey")
    public void successful_selection_encryption_image_owner_Localkey() throws InterruptedException, AWTException {
        driver.navigate().refresh();
        imageprotectorPage.EncryptImage_Selection(imageOrigin, driver);
        imageprotectorPage.saveFile_image.click();
        Thread.sleep(5000);
        page.save_files(encryptedSelectionimg);
        Assert.assertTrue(imageprotectorPage.viewencryptedBTN.isEnabled());
        Assert.assertFalse(imageprotectorPage.encryptDisabledbtn.isEnabled());
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertFalse(imageprotectorPage.removeSelectedbtn.isEnabled());
        imageprotectorPage.editBtn2.click();
        WebElement tracerid = imageprotectorPage.tracerDetails.get(0);
        tracerIDselectionLocalKey = tracerid.getText();
        System.out.print(" this is tracer for selection ");
        System.out.print(tracerIDselectionLocalKey);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        Thread.sleep(2000);
    }
    @Test(priority = 3,dependsOnMethods = "successful_Whole_encryption_image_withLocalKey")
    public void successful_whole_decryption_image_owner_LocalKey() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        imageprotectorPage.decryptIMG_PDF_LocalKey(encryptedImage,driver ,true, Localkeyowner);
        imageprotectorPage.saveFile_image.click();
        Thread.sleep(2000);
        page.save_files(DecryptedImage);
        Assert.assertTrue(imageprotectorPage.encryptWholeBtn.isDisplayed());
        imageprotectorPage.editBtn2.click();
        Assert.assertEquals(imageprotectorPage.textAfterdecryption.getText() , "This Image does not contain encryption.");
        boolean b = page.CompareFiles_Hashfn(imageOrigin,DecryptedImage);
        //Assert.assertTrue(b);
        loginobject.logout();
        Thread.sleep(3000);
    }
    @Test(priority = 4,dependsOnMethods = {"successful_Whole_encryption_image_withLocalKey","successful_whole_decryption_image_owner_LocalKey"})
    public void successful_FullDecyrption_ViewEncryptedContent_owner_localkey() throws AWTException, InterruptedException, IOException {
        Thread.sleep(3000);
        homeobject.individual_login();
        loginobject.LOGIN(usernameQALocalkey, passwordQALocalkey);
        imageprotectorPage.imagebtn.click();
        imageprotectorPage.choosefilebtn.sendKeys(encryptedImage);
        imageprotectorPage.loadkey.sendKeys(Localkeyowner);
        Thread.sleep(4000);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        System.out.print(" after view encrypted full decryption tracer ");
        System.out.print(tracerIDlocalKey);
        Assert.assertEquals(imageprotectorPage.tracerDetails.get(0).getText(), tracerIDlocalKey);
        imageprotectorPage.editBtn2.click();
        Assert.assertTrue(imageprotectorPage.reEncryptbtn.isDisplayed());
        imageprotectorPage.reEncryptbtn.click();
        Assert.assertTrue(imageprotectorPage.viewencryptedBTN.isDisplayed());
        Assert.assertEquals(imageprotectorPage.txtAfterfullencryption.getText() , "This Image is fully encrypted and cannot be modified. To edit the Image, the encryption must first be removed.");
        Assert.assertTrue(imageprotectorPage.removeallencryptionBTN.isEnabled());
        loginobject.logout();
        Thread.sleep(3000);
    }
    @Test(priority = 5,dependsOnMethods = {"successful_Whole_encryption_image_withLocalKey","successful_whole_decryption_image_owner_LocalKey"})
    public void successful_FullDecyrption_removeAll_owner_Localkey() throws AWTException, InterruptedException, IOException {
        Thread.sleep(3000);
        homeobject.individual_login();
        loginobject.LOGIN(usernameQALocalkey, passwordQALocalkey);
        imageprotectorPage.imagebtn.click();
        imageprotectorPage.choosefilebtn.sendKeys(encryptedImage);
        Thread.sleep(2000);
        imageprotectorPage.loadkey.sendKeys(Localkeyowner);
        imageprotectorPage.editBtn2.click();
        imageprotectorPage.removeallencryptionBTN.click();
        Thread.sleep(3000);
        Assert.assertTrue(imageprotectorPage.removeCONFIDENCIAL.isEnabled());
        Assert.assertTrue(imageprotectorPage.encryptWholeBtn.isEnabled());
        imageprotectorPage.editBtn2.click();
        System.out.print("after remove all decryotion full tracer id ");
        System.out.print(tracerIDlocalKey);
        Assert.assertTrue(imageprotectorPage.tracerName.isDisplayed());
        Assert.assertEquals(imageprotectorPage.tracerDetails.get(0).getText(), tracerIDlocalKey);
    }
}
