package Tests;

import Data.LoadProperties;
import Pages.EncryptionToolsPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EncryptionToolsTest extends TestBase {

    HomePage homeobject;
    LoginPage loginobject;
    PageBase page;
    EncryptionToolsPage encryptionToolsObject;
    static String Decrypted_content;
    static WebDriverWait wait;
    static String unEncrypted_content = "this is encrypted to ingy.samer.97";
    static String encrypted_content;
    static List<String> list_recipients = new ArrayList<String>();
    String videoPath = LoadProperties.testData.getProperty("VideopathORIGIN");
    String DecryptedVideoPath = LoadProperties.testData.getProperty("SavedDecryptedVideo");
    String uploadedVideoPATH = LoadProperties.testData.getProperty("SavedEncryptedVideo");
    String zipPath = LoadProperties.testData.getProperty("ZIPpathORIGIN");
    String EncryptedZip = LoadProperties.testData.getProperty("savedEncryptedZip");
    String DecryptedZIP = LoadProperties.testData.getProperty("savedDecryptedZip");
    String uploadedtxtPATH = LoadProperties.testData.getProperty("savedEncryptedTXT");
    String DecryptedTXTPath = LoadProperties.testData.getProperty("savedDecryptedTXT");
    String txtPath = LoadProperties.testData.getProperty("TxtpathORIGN");
    String excelPath = LoadProperties.testData.getProperty("excelPATH");
    String uploadedexcelPATH = LoadProperties.testData.getProperty("savedexcelPATH");
    String wordPath = LoadProperties.testData.getProperty("wordPATH");

    String uploadedwordPATH = LoadProperties.testData.getProperty("savedwordPATH");
    public  static String UsernamePROD = LoadProperties.testData.getProperty("usernamePROD");
    public  static String passwordPROD = LoadProperties.testData.getProperty("passwordPROD");
    public  static String usernameQA = LoadProperties.testData.getProperty("usernameQA");
    public  static String passwordQA = LoadProperties.testData.getProperty("passwordQA");
    public EncryptionToolsTest() throws AWTException {

    }

    @Test(priority = 1 )
    public void successful_text_encryption() throws InterruptedException, AWTException, IOException, NoSuchAlgorithmException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        encryptionToolsObject = new EncryptionToolsPage(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        encryptionToolsObject.encrypt_text(unEncrypted_content);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result));
        list_recipients = EncryptionToolsPage.get_list_of_recipients();
        System.out.print(" text encryption: ");
        System.out.print(encryptionToolsObject.encryption_result.isDisplayed());
        Assert.assertTrue(encryptionToolsObject.encryption_result.isDisplayed());
        encrypted_content = encryptionToolsObject.encryption_result.getText();
    }
    @Test(priority = 2 ,dependsOnMethods = "successful_text_encryption")
    public void Successful_Video_Encryption() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        encryptionToolsObject.encryptFiles(videoPath);
       System.out.print(videoPath);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(" video encryption: ");
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
      encryptionToolsObject.Save_As_Btn.click();
        page.save_files(uploadedVideoPATH);
      //encryptionToolsObject.save_files(uploadedVideoPATH);
    }

    @Test(priority = 3, dependsOnMethods = "successful_text_encryption")
    public void Successful_txt_Encryption() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        encryptionToolsObject.encryptFiles(txtPath);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(" txt file encryption: ");
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
      //  encryptionToolsObject.save_files(uploadedtxtPATH);
        encryptionToolsObject.Save_As_Btn.click();
        page.save_files(uploadedtxtPATH);
    }
    @Test(priority = 4, dependsOnMethods = "successful_text_encryption")
    public void Successful_excel_Encryption() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        encryptionToolsObject.encryptFiles(excelPath);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(" excel encryption: ");
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
        //encryptionToolsObject.save_files(uploadedexcelPATH);
        encryptionToolsObject.Save_As_Btn.click();
        page.save_files(uploadedexcelPATH);
    }
    @Test(priority = 5, dependsOnMethods = "successful_text_encryption")
    public void Successful_word_Encryption() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        encryptionToolsObject.encryptFiles(wordPath);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(" word encryption: ");
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
      //  encryptionToolsObject.save_files(uploadedwordPATH);
        encryptionToolsObject.Save_As_Btn.click();
        page.save_files(uploadedwordPATH);
    }
    @Test(priority = 6, dependsOnMethods = "successful_text_encryption" )
    public void Successful_ZIP_Encryption() throws AWTException, InterruptedException, IOException {
       driver.navigate().refresh();
        encryptionToolsObject.encryptFiles(zipPath);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(" ZIP encryption: ");
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
     //   encryptionToolsObject.save_files(EncryptedZip);
        encryptionToolsObject.Save_As_Btn.click();
        page.save_files(EncryptedZip);

    }

    @Test(priority = 7, dependsOnMethods = "successful_text_encryption")
    public void successful_text_decryption_owner_with_noKey() throws InterruptedException {
        driver.navigate().refresh();
        encryptionToolsObject.decrypt_text(encrypted_content);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result));
        Assert.assertEquals(encryptionToolsObject.encryption_result.getText(), unEncrypted_content);
        System.out.print(" text decryption: ");
        System.out.print(encryptionToolsObject.encryption_result.getText());
    }

    @Test(priority = 8,dependsOnMethods = {"Successful_Video_Encryption" ,"successful_text_encryption"})
    public void successful_Video_decryption_owner_with_noKey() throws InterruptedException, AWTException, IOException {
        driver.navigate().refresh();
        encryptionToolsObject.filesDecryption(uploadedVideoPATH);
        System.out.print(" Video decryption : ");
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
       // encryptionToolsObject.save_files(DecryptedVideoPath);
        encryptionToolsObject.Save_As_Btn.click();
        page.save_files(DecryptedVideoPath);
        Boolean test = page.CompareFiles_Hashfn(DecryptedVideoPath ,videoPath);
        Assert.assertTrue(test);
    }
    @Test(priority = 9 , dependsOnMethods = {"Successful_ZIP_Encryption","successful_text_encryption"})
    public void successful_ZIP_decryption_owner_with_noKey() throws InterruptedException, AWTException, IOException {
        driver.navigate().refresh();
        encryptionToolsObject.filesDecryption(EncryptedZip);
        System.out.print(" ZIP decryption : ");
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
      //  encryptionToolsObject.save_files(DecryptedZIP);
       encryptionToolsObject.Save_As_Btn.click();
        page.save_files(DecryptedZIP);
        Boolean test = page.CompareFiles_Hashfn(zipPath ,DecryptedZIP);
       // Assert.assertTrue(test);
    }

    @Test(priority = 10, dependsOnMethods = {"successful_text_encryption" , "Successful_txt_Encryption"})
    public void successful_txt_decryption_owner_with_noKey() throws InterruptedException, AWTException, IOException {
        driver.navigate().refresh();
        encryptionToolsObject.filesDecryption(uploadedtxtPATH);
        System.out.print(" TXT decryption : ");
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        //encryptionToolsObject.save_files(DecryptedTXTPath);
       encryptionToolsObject.Save_As_Btn.click();
        page.save_files(DecryptedTXTPath);
        Boolean comparee = page.CompareFiles_Hashfn(txtPath , DecryptedTXTPath);
        Assert.assertTrue(comparee);
    }

    @Test(priority = 11 , dependsOnMethods = {"successful_text_encryption"})
    public void successful_text_decryption_recipient_with_noKey() throws InterruptedException, AWTException {
        loginobject.logout();
        homeobject.individual_login();
        loginobject.LOGIN(list_recipients.get(0), passwordQA);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        encryptionToolsObject.decrypt_text(encrypted_content);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".h-64.border-2.border-black.w-full.p-4")));
        System.out.print(" text decryption for recipient: ");
        Assert.assertEquals(encryptionToolsObject.encryption_result.getText(), unEncrypted_content);
        System.out.print(encryptionToolsObject.encryption_result.getText());
    }

    @Test(priority = 12, dependsOnMethods = {"successful_text_decryption_recipient_with_noKey","successful_text_encryption"})
    public void successful_Video_decryption_recipient_with_noKey() throws InterruptedException, AWTException {
        driver.navigate().refresh();
        encryptionToolsObject.filesDecryption(uploadedVideoPATH);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flex-1.h-64.w-full.flex.flex-col.my-auto.items-center.justify-center.p-4.border-2.border-gray-400.bg-slate-100.rounded")));
        System.out.print(" decryption Video for recipients: ");
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
    }
    @Test(priority = 13 , dependsOnMethods = {"successful_text_decryption_recipient_with_noKey","successful_text_encryption"})
    public void successful_txt_decryption_recipient_with_noKey() throws InterruptedException, AWTException, IOException {
        driver.navigate().refresh();
        encryptionToolsObject.filesDecryption(uploadedtxtPATH);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(encryptionToolsObject.encryption_result_files));
        System.out.print(" decryption TXT for recipients: ");
        Assert.assertTrue(encryptionToolsObject.encryption_result_files.isDisplayed());
        System.out.print(encryptionToolsObject.encryption_result_files.isDisplayed());
        loginobject.logout();
    }
}