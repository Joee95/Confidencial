package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Pages.EncryptionToolsPage.List_of_recipients;
import static Pages.ImageprotectorPage.viewencryptedBTN;

public class PDFProtectorPage extends PageBase {

    public PDFProtectorPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    static Robot robot;

    public static WebDriverWait wait;
    static List<String> list_recipients = new ArrayList<String>();
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[1]/div[1]/div[2]/div[2]")
    public static WebElement PDFprotector;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[1]/div/input")
    public static WebElement PDFarea;
    @FindBy(xpath = "//*[@id=\"headlessui-tabs-panel-18\"]/div[1]/div/div[4]/div/div[1]/button")
    public static WebElement EncryptPDFbtn;

    @FindBy(id = "save-button")
    public static WebElement Savebtn_pdf;

    @FindBy(xpath = "//div[@class='font-semibold text-base']")
    public static WebElement ConfidentialText;

    @FindBy(xpath = "//*[@id=\"pageWidgetContainer1\"]")
    public static WebElement PageContainer;

    @FindBy(className = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[1]/div/div/div/div/div/div[1]/button")
    public static WebElement choosefilebtn;

    @FindBy(xpath = "//div[@class='text-2xs']")
    public static List<WebElement> tracerDetails;

    @FindBy(xpath = "//button[@class='px-1 pb-2 pt-3 leading-5 font-bold w-1/2 -mb-[1px] transition-all transition-colors transition-opacity border-b border-l border-t border-r rounded-t-lg duration-300 text-gray-400 border-transparent hover:border-gray-100 hover:border-b-gray-200 hover:bg-white hover:bg-opacity-80 hover:text-gray-500 bg-transparent' and @type='button']")
    public static WebElement editBtn2;

    @FindBy(xpath = "//button[@class='px-1 pb-2 pt-3 leading-5 font-bold w-1/2 -mb-[1px] transition-all transition-colors transition-opacity border-b border-l border-t border-r rounded-t-lg duration-300 text-gray-400 border-transparent hover:border-gray-100 hover:border-b-gray-200 hover:bg-white hover:bg-opacity-80 hover:text-gray-500 bg-transparent' and contains (., 'Detail')]")
    public static WebElement DetailBtn;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-error-dark border border-react-toast-red text-white hover:opacity-90 cursor-pointer w-full px-3 py-1 font-semibold' and @type='button']")
    public static WebElement reEncryptbtn;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-white border border-gray-400 hover:bg-gray-100 cursor-pointer w-full px-3 py-1 font-semibold' and @type='button']")
    public static WebElement removeallencryptionBTN;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/div/div/button/div")
    public static WebElement ViewEncryptedContent;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-blue border border-c11-blue text-white bg-c11-error-dark border border-react-toast-red text-white hover:opacity-90 cursor-pointer px-3 py-1 font-semibold' and @type='button']")
    public static WebElement RemoveConfidencialAlert;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/div[3]/div/div[1]/div/div[4]/div/div[1]/div/button")
    public static WebElement DropdownArrow;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/div[3]/div/div[1]/div/div[4]/div/div[1]/div/div/div/div[5]/button/div/div")
    public static WebElement Find_and_Encrypt;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[2]/div[3]/div/div[1]/div/div[4]/div/div[1]/div/div/div/div[4]/button/div/div")
    public static WebElement Page_Range_Encrypt;

    @FindBy(xpath = "//*[@id=\"SearchPanel__input\"]")
    public static WebElement Search_Document;

    @FindBy(xpath = "//input[@type='number' and @class='ms-TextField-field field-96']")
    public static List<WebElement> Page_Numbers;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-blue border border-c11-blue text-white hover:opacity-90 cursor-pointer w-full w-full px-3 py-2.5 font-semibold text-lg' and @type='button']")
    public static WebElement encryptWholeBtn;

    @FindBy(css = ".break-words.opacity-50")
    public static List<WebElement> List_of_recipients;

    @FindBy(xpath = "//div[@class='font-semibold text-base']")
    public static WebElement Remove_Confidential_Text;

    @FindBy(xpath = "//div[@class='flex flex-row items-center justify-center gap-x-2' and contains ( . , 'Remove Selected Encryption' )]")
    public static WebElement RemoveSelectedEncryptionBtn;

    @FindBy(id = "file")
    public static WebElement loadkey;

    public static List<String> get_list_of_recipients() {
        System.out.println(List_of_recipients.size());
        List<String> names_recipients = new ArrayList<String>();
        for (WebElement web_Element : List_of_recipients) {
            names_recipients.add(web_Element.getText());
        }
        return names_recipients;
    }

    public static void encrypt_PDF(String PDF_File, WebDriver driver) throws InterruptedException {
        PDFarea.sendKeys(PDF_File);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(encryptWholeBtn));
    }

    public static void Decrypt_PDF_Whole(String SavedPDF, WebDriver driver) {
        PDFarea.sendKeys(SavedPDF);
    }

    public static void Decrypt_PDF_Find_and_Encrypt(String SavedPDF, WebDriver driver) {
        PDFarea.sendKeys(SavedPDF);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.visibilityOf(removeallencryptionBTN));
        ViewEncryptedContent.click();
    }

    public static void Decrypt_For_Recipient(String SavedPDF, WebDriver driver) {
        PDFarea.sendKeys(SavedPDF);
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //wait.until(ExpectedConditions.visibilityOf(reEncryptbtn));
    }

    public static void EncryptPDF_Selection(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        // Click and hold the mouse button on the element (e.g., imageElement)
        Actions actions = new Actions(driver);
        actions.clickAndHold(PageContainer).perform();
        // Move the cursor to a specific X and Y offset (adjust as needed)
        int xOffset = 50; // Replace with your desired X offset
        int yOffset = 50; // Replace with your desired Y offset
        actions.moveByOffset(xOffset, yOffset).perform();
        actions.release().perform();
        EncryptPDFbtn.click();
        Thread.sleep(1000);
    }

    public void SaveEncryptedPDF(String SavedPDFPath) throws InterruptedException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(SavedPDFPath);
        clipboard.setContents(selection, null);
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

    public void encrypt_PDF2(String text) throws InterruptedException {
        click(PDFprotector);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
        click(choosefilebtn);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.visibilityOf(EncryptPDFbtn));
        EncryptPDFbtn.click();
    }

    public static void Remove_Confidencial(WebDriver driver, boolean b) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(ViewEncryptedContent));
         /*if boolean b is true then we are doing full decryption and if it's false then
         we don't need to select edit button, and we are in selection decryption*/
        if (b == true) {
        }
        reEncryptbtn.click();
        Thread.sleep(2000);
        editBtn2.click();
        removeallencryptionBTN.click();
        Thread.sleep(3000);
        removeallencryptionBTN.click();
        Thread.sleep(200);
        RemoveConfidencialAlert.click();
        Thread.sleep(1000);
    }

    public void Find_and_Encrypt(String PDF_File, String search_document, WebDriver driver) throws InterruptedException {
        PDFarea.sendKeys(PDF_File);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(DropdownArrow));
        DropdownArrow.click();
        Thread.sleep(500);
        Find_and_Encrypt.click();
        driver.switchTo().frame(0);
        Search_Document.sendKeys(search_document);
        driver.switchTo().defaultContent();
        wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(encryptWholeBtn));
        encryptWholeBtn.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(ViewEncryptedContent));
    }

    public void Page_Range_Encryption(String PDF_File, String from, String to, WebDriver driver) throws InterruptedException {
        PDFarea.sendKeys(PDF_File);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(DropdownArrow));
        DropdownArrow.click();
        Thread.sleep(500);
        Page_Range_Encrypt.click();
        Page_Numbers.get(0).sendKeys(from);
        Page_Numbers.get(0).sendKeys(to);
        wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(encryptWholeBtn));
        encryptWholeBtn.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(ViewEncryptedContent));
    }

    public static void decrypt_PDF_LocalKey(String Path, WebDriver driver, boolean b, String key) throws InterruptedException {
        PDFarea.sendKeys(Path);
        Thread.sleep(500);
        loadkey.sendKeys(key);
        // if boolean b is true then we are doing full decryption and if it's false then we dont need to select edit button and we are in selction decryption
        if (b == true) {
        }
        reEncryptbtn.click();
        Thread.sleep(2000);
        editBtn2.click();
        removeallencryptionBTN.click();
        Thread.sleep(3000);
        removeallencryptionBTN.click();
        Thread.sleep(200);
        RemoveConfidencialAlert.click();
        Thread.sleep(1000);
    }

    public static void decrypt_PDF_LocalKey_Without_Removing_Confidencial(String Path, WebDriver driver, boolean b, String key) throws InterruptedException {
        PDFarea.sendKeys(Path);
        Thread.sleep(500);
        loadkey.sendKeys(key);
        // if boolean b is true then we are doing full decryption and if it's false then we dont need to select edit button and we are in selction decryption
        if (b == true) {
        }
    }
}


