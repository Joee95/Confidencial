package Tests;

import Data.LoadProperties;
import Pages.*;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import static Tests.EncryptionToolsTest.*;
import static Tests.ImageprotectorTest.tracerID;

public class PDF_ProtectorTest_With_LocalStorageKey extends TestBase {

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
    public static String Local_Storage_Key = LoadProperties.testData.getProperty("Local_Storage_Key");


    public PDF_ProtectorTest_With_LocalStorageKey() throws AWTException {

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
        loginobject.logout();
    }

    @Test(priority = 4, dependsOnMethods = "successful_Page_Range_Encryption_PDF")
    public void successful_whole_decryption_PDF_owner_LocalKey() throws AWTException, InterruptedException, IOException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        pdfProtectorPage.PDFprotector.click();
        PDFProtectorPage.decrypt_PDF_LocalKey(SvedpdfPATH, driver, true, Local_Storage_Key);
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
        loginobject.logout();
    }

    @Test(priority = 5, dependsOnMethods = "successful_whole_decryption_PDF_owner_LocalKey")
    public void successful_Find_and_Encrypt_decryption_PDF_owner_LocalKey() throws AWTException, InterruptedException, IOException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        pdfProtectorPage.PDFprotector.click();
        PDFProtectorPage.decrypt_PDF_LocalKey(Saved_Find_and_Encrypt, driver, true, Local_Storage_Key);
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
        loginobject.logout();
    }

    @Test(priority = 6, dependsOnMethods = "successful_Find_and_Encrypt_decryption_PDF_owner_LocalKey")
    public void successful_Page_Range_decryption_PDF_owner_LocalKey() throws AWTException, InterruptedException, IOException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        pdfProtectorPage.PDFprotector.click();
        PDFProtectorPage.decrypt_PDF_LocalKey(Saved_Page_Range_Encryption, driver, true, Local_Storage_Key);
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
    }
}
