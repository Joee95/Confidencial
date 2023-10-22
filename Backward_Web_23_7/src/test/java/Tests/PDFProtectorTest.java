package Tests;

import Data.LoadProperties;
import Pages.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.time.Duration;

import static Tests.EncryptionToolsTest.*;
import static Tests.ImageprotectorTest.tracerID;
import static Tests.PDF_ProtectorTest_With_LocalStorageKey.Local_Storage_Key;

public class PDFProtectorTest extends TestBase {
    static HomePage homeobject;
    static LoginPage loginobject;
    static EncryptionToolsPage encryptionToolsPage;
    static ImageprotectorPage imageprotectorPage;
    static PDFProtectorPage pdfProtectorPage;
    static PageBase page;
    static String tracerID_Whole;
    static String tracerID_Find_and_Encrypt;
    static String tracerID_Page_Range;
    String originPDF = LoadProperties.testData.getProperty("originPDF");
    static String SvedpdfPATH = LoadProperties.testData.getProperty("SvedpdfPATH");
    static String Save_after_removing_Confidencial_Whole = LoadProperties.testData.getProperty("Save_after_removing_Confidencial_Whole");
    static String Save_after_removing_Confidencial_Find_and_Encrypt = LoadProperties.testData.getProperty("Save_after_removing_Confidencial_Find_and_Encrypt");
    static String Save_after_removing_Confidencial_Page_Range = LoadProperties.testData.getProperty("Save_after_removing_Confidencial_Page_Range");

    static String search_document = LoadProperties.testData.getProperty("search_document");
    static String Saved_Find_and_Encrypt = LoadProperties.testData.getProperty("Saved_Find_and_Encrypt");
    static String from = LoadProperties.testData.getProperty("from");
    static String to = LoadProperties.testData.getProperty("to");

    static String Saved_Page_Range_Encryption = LoadProperties.testData.getProperty("Saved_Page_Range_Encryption");


    public PDFProtectorTest() throws AWTException {
    }

    @Test(priority = 1)
    public void successful_Whole_encryption_PDF() throws AWTException, InterruptedException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        pdfProtectorPage = new PDFProtectorPage(driver);
        pdfProtectorPage.PDFprotector.click();
        pdfProtectorPage.encrypt_PDF(originPDF, driver);
        list_recipients = pdfProtectorPage.get_list_of_recipients();
        pdfProtectorPage.encryptWholeBtn.click();
        Assert.assertTrue(PDFProtectorPage.ViewEncryptedContent.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.ViewEncryptedContent.isEnabled());
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID_Whole = tracerid.getText();
        System.out.print("this is tracer for full PDF encryption : " + tracerID_Whole);
        PDFProtectorPage.editBtn2.click();
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        driver.switchTo().frame(0);
        pdfProtectorPage.Savebtn_pdf.click();
        Thread.sleep(7000);
        driver.switchTo().defaultContent();
        page.save_files(SvedpdfPATH);
    }

    @Test(priority = 2, dependsOnMethods = "successful_Whole_encryption_PDF")
    public void successful_Find_and_Encrypt_PDF() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        pdfProtectorPage.PDFprotector.click();
        pdfProtectorPage.Find_and_Encrypt(originPDF, search_document, driver);
        Assert.assertTrue(PDFProtectorPage.ViewEncryptedContent.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.ViewEncryptedContent.isEnabled());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertTrue(PDFProtectorPage.RemoveSelectedEncryptionBtn.isDisplayed());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID_Find_and_Encrypt = tracerid.getText();
        System.out.print("this is tracer for Find and Encrypt PDF encryption : " + tracerID_Find_and_Encrypt);
        driver.switchTo().frame(0);
        pdfProtectorPage.Savebtn_pdf.click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        page.save_files(Saved_Find_and_Encrypt);
    }

    @Test(priority = 3, dependsOnMethods = "successful_Find_and_Encrypt_PDF")
    public void successful_Page_Range_Encryption_PDF() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        pdfProtectorPage.PDFprotector.click();
        pdfProtectorPage.Page_Range_Encryption(originPDF, from, to, driver);
        Assert.assertTrue(PDFProtectorPage.ViewEncryptedContent.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.ViewEncryptedContent.isEnabled());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertTrue(PDFProtectorPage.RemoveSelectedEncryptionBtn.isDisplayed());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID_Page_Range = tracerid.getText();
        System.out.print("this is tracer for Page Range PDF encryption : " + tracerID_Page_Range);
        PDFProtectorPage.editBtn2.click();
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        driver.switchTo().frame(0);
        pdfProtectorPage.Savebtn_pdf.click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        page.save_files(Saved_Page_Range_Encryption);
    }

    @Test(priority = 4, dependsOnMethods = "successful_Page_Range_Encryption_PDF")
    public void Decrypt_Whole_PDF_ViewEncryptedContent() throws InterruptedException {
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_PDF_Whole(SvedpdfPATH, driver);
        //PDFProtectorPage.decrypt_PDF_LocalKey_Without_Removing_Confidencial(SvedpdfPATH, driver, true, Local_Storage_Key);
        Thread.sleep(3000);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        PDFProtectorPage.editBtn2.click();
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID = tracerid.getText();
        System.out.print("this is tracer for Whole PDF View Encrypted Content : " + tracerID);
        Assert.assertEquals(PDFProtectorPage.tracerDetails.get(0).getText(), tracerID_Whole);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isEnabled());
        PDFProtectorPage.reEncryptbtn.click();
    }

    @Test(priority = 5, dependsOnMethods = "Decrypt_Whole_PDF_ViewEncryptedContent")
    public void Decrypt_Find_and_Encrypt_PDF_ViewEncryptedContent() throws InterruptedException {
        driver.navigate().refresh();
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_PDF_Find_and_Encrypt(Saved_Find_and_Encrypt, driver);
        Thread.sleep(5000);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertTrue(PDFProtectorPage.RemoveSelectedEncryptionBtn.isDisplayed());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID = tracerid.getText();
        System.out.print("this is tracer for Find and Encrypt PDF View Encrypted Content: " + tracerID);
        Assert.assertEquals(PDFProtectorPage.tracerDetails.get(0).getText(), tracerID_Find_and_Encrypt);
        PDFProtectorPage.reEncryptbtn.click();
    }

    @Test(priority = 6, dependsOnMethods = "Decrypt_Find_and_Encrypt_PDF_ViewEncryptedContent")
    public void Decrypt_Page_Range_PDF_ViewEncryptedContent() throws InterruptedException {
        driver.navigate().refresh();
        PDFProtectorPage.Decrypt_PDF_Find_and_Encrypt(Saved_Page_Range_Encryption, driver);
        Assert.assertTrue(pdfProtectorPage.removeallencryptionBTN.isDisplayed());
        Thread.sleep(5000);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.removeallencryptionBTN.isEnabled());
        Assert.assertTrue(PDFProtectorPage.RemoveSelectedEncryptionBtn.isDisplayed());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID = tracerid.getText();
        System.out.print("this is tracer for Page Range PDF View Encrypted Content : " + tracerID);
        Assert.assertEquals(PDFProtectorPage.tracerDetails.get(0).getText(), tracerID_Page_Range);
        PDFProtectorPage.reEncryptbtn.click();
    }

    @Test(priority = 7, dependsOnMethods = "Decrypt_Page_Range_PDF_ViewEncryptedContent")
    public void successful_whole_RemoveConfidential_PDF_owner_nokey() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_PDF_Whole(SvedpdfPATH, driver);
        PDFProtectorPage.Remove_Confidencial(driver, true);
        PDFProtectorPage.DetailBtn.click();
        Assert.assertTrue(PDFProtectorPage.Remove_Confidential_Text.isDisplayed());
        Assert.assertEquals(PDFProtectorPage.Remove_Confidential_Text.getText(), "This PDF does not contain encryption.");
        driver.switchTo().frame(0);
        pdfProtectorPage.Savebtn_pdf.click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        page.save_files(Save_after_removing_Confidencial_Whole);
        boolean b = page.CompareFiles_Hashfn(originPDF, Save_after_removing_Confidencial_Whole);
        Thread.sleep(3000);
    }

    @Test(priority = 8, dependsOnMethods = "successful_whole_RemoveConfidential_PDF_owner_nokey")
    public void successful_Find_and_Encryption_RemoveConfidential_owner_nokey() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_PDF_Whole(Saved_Find_and_Encrypt, driver);
        PDFProtectorPage.Remove_Confidencial(driver, true);
        PDFProtectorPage.DetailBtn.click();
        Assert.assertTrue(PDFProtectorPage.Remove_Confidential_Text.isDisplayed());
        Assert.assertEquals(PDFProtectorPage.Remove_Confidential_Text.getText(), "This PDF does not contain encryption.");
        driver.switchTo().frame(0);
        pdfProtectorPage.Savebtn_pdf.click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        page.save_files(Save_after_removing_Confidencial_Find_and_Encrypt);
        boolean b = page.CompareFiles_Hashfn(originPDF, Save_after_removing_Confidencial_Find_and_Encrypt);
        Thread.sleep(3000);
    }

    @Test(priority = 9, dependsOnMethods = "successful_Find_and_Encryption_RemoveConfidential_owner_nokey")
    public void successful_Page_Range_RemoveConfidential_owner_nokey() throws AWTException, InterruptedException, IOException {
        driver.navigate().refresh();
        driver.switchTo().alert().accept();
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_PDF_Whole(Saved_Page_Range_Encryption, driver);
        PDFProtectorPage.Remove_Confidencial(driver, true);
        PDFProtectorPage.DetailBtn.click();
        Assert.assertTrue(PDFProtectorPage.Remove_Confidential_Text.isDisplayed());
        Assert.assertEquals(PDFProtectorPage.Remove_Confidential_Text.getText(), "This PDF does not contain encryption.");
        driver.switchTo().frame(0);
        pdfProtectorPage.Savebtn_pdf.click();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        page.save_files(Save_after_removing_Confidencial_Page_Range);
        boolean b = page.CompareFiles_Hashfn(originPDF, Save_after_removing_Confidencial_Page_Range);
        Thread.sleep(3000);
        loginobject.logout();
    }

    @Test(priority = 10, dependsOnMethods = "successful_Page_Range_RemoveConfidential_owner_nokey")
    public void successful_Whole_PDF_decryption_recipient_with_noKey() throws InterruptedException, AWTException {
        homeobject.individual_login();
        loginobject.LOGIN(list_recipients.get(0), passwordQA);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_For_Recipient(SvedpdfPATH, driver);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isEnabled());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID = tracerid.getText();
        System.out.print("this is tracer for Whole PDF View Encrypted Content For Recipient : " + tracerID);
        Assert.assertEquals(PDFProtectorPage.tracerDetails.get(0).getText(), tracerID_Whole);
        PDFProtectorPage.reEncryptbtn.click();
    }

    @Test(priority = 11, dependsOnMethods = {"successful_Whole_PDF_decryption_recipient_with_noKey"})
    public void successful_Find_and_Encrypt_PDF_decryption_recipient_with_noKey() throws InterruptedException, AWTException {
        driver.navigate().refresh();
        Thread.sleep(1000);
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_For_Recipient(Saved_Find_and_Encrypt, driver);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isEnabled());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID = tracerid.getText();
        System.out.print("this is tracer for Find and Encrypt PDF View Encrypted Content For Recipient : " + tracerID);
        Assert.assertEquals(PDFProtectorPage.tracerDetails.get(0).getText(), tracerID_Find_and_Encrypt);
        PDFProtectorPage.reEncryptbtn.click();
    }

    @Test(priority = 12, dependsOnMethods = {"successful_Find_and_Encrypt_PDF_decryption_recipient_with_noKey"})
    public void successful_Page_Range_PDF_decryption_recipient_with_noKey() throws InterruptedException, AWTException {
        driver.navigate().refresh();
        Thread.sleep(1000);
        PDFProtectorPage.PDFprotector.click();
        PDFProtectorPage.Decrypt_For_Recipient(Saved_Page_Range_Encryption, driver);
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isDisplayed());
        Assert.assertTrue(PDFProtectorPage.reEncryptbtn.isEnabled());
        PDFProtectorPage.DetailBtn.click();
        WebElement tracerid = PDFProtectorPage.tracerDetails.get(0);
        tracerID = tracerid.getText();
        System.out.print("this is tracer for Page Range PDF View Encrypted Content For Recipient : " + tracerID);
        Assert.assertEquals(PDFProtectorPage.tracerDetails.get(0).getText(), tracerID_Page_Range);
        PDFProtectorPage.reEncryptbtn.click();
    }
}
