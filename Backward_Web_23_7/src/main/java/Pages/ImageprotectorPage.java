package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.time.Duration;
import java.util.List;

public class ImageprotectorPage extends PageBase {

    Robot r = new Robot();

    public ImageprotectorPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[1]/div[1]/div[2]/div[3]/div")
    public static WebElement imagebtn;
    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-blue border border-c11-blue text-white opacity-50 w-full w-full px-3 py-2.5 font-semibold text-lg' and @type='button']")
    public static WebElement encryptDisabledbtn;

    @FindBy(xpath = "//div[@class='font-semibold text-base']")
    public
    WebElement textAfterdecryption;

    @FindBy(xpath = "//div[@class='flex flex-row items-start w-full ml-0.5 w-full text-sm font-semibold text-c11-text-subtitle cursor-pointer py-1.5 truncate']")
    public
    WebElement tracerName;
    @FindBy(xpath = "//div[@class='text-2xs']")
    public static List<WebElement> tracerDetails;


    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-blue border border-c11-blue text-white hover:opacity-90 cursor-pointer w-full w-full px-3 py-2.5 font-semibold text-lg' and @type='button']")
    public static WebElement encryptWholeBtn;

    @FindBy(xpath = "//button[@class='px-1 pb-2 pt-3 leading-5 font-bold w-1/2 -mb-[1px] transition-all transition-colors transition-opacity border-b border-l border-t border-r rounded-t-lg duration-300 text-gray-400 border-transparent hover:border-gray-100 hover:border-b-gray-200 hover:bg-white hover:bg-opacity-80 hover:text-gray-500 bg-transparent' and @type='button']")
    public
    WebElement editBtn2;
    @FindBy(css = "px-1.pb-2.pt-3.leading-5.font-bold.w-1/2.-mb-[1px].transition-all.transition-colors.transition-opacity.border-b.border-l.border-t.border-r.rounded-t-lg.text-c11-primary.500.border-gray-200.border-b-white.bg-white.rounded-t-lg")
    WebElement editbtn;
    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-white border border-gray-400 hover:bg-gray-100 cursor-pointer w-full px-3 py-1 font-semibold' and @type='button']")
    public
    WebElement removeallencryptionBTN;
    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-white border border-gray-400 opacity-50 w-full px-3 py-1 font-semibold' and @type='button']")
    public static WebElement removeSelectedbtn;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-white border border-gray-400 hover:bg-gray-100 cursor-pointer w-full px-3 py-1 font-semibold' and @type='button']")
    public WebElement removeCONFIDENCIAL;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-blue border border-c11-blue text-white bg-c11-error-dark border border-react-toast-red text-white hover:opacity-90 cursor-pointer px-3 py-1 font-semibold' and @type='button']")
    WebElement RemoveConfidencialAlert;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[1]/div/div/div[1]/div[2]/button[2]")
    public static  WebElement saveFile_image;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div/div[1]/div/input")
    public static WebElement choosefilebtn;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-error-dark border border-react-toast-red text-white hover:opacity-90 cursor-pointer w-full px-3 py-1 font-semibold' and @type='button']")
    public static WebElement reEncryptbtn;

    @FindBy(xpath = "//button[@class='rounded select-none whitespace-nowrap bg-c11-blue border border-c11-blue text-white hover:opacity-90 cursor-pointer w-full px-3 py-1 font-semibold' and @type='button']")
    public static WebElement viewencryptedBTN;

    @FindBy(xpath = "//img[@class='select-none h-auto inline-block shadow-md']")
    public static WebElement Image;

    @FindBy(xpath = "//div[@class='flex flex-row items-start pt-8 pr-8 py-3 text-c11-text-body w-full']")
    public static WebElement txtAfterfullencryption;

    @FindBy(xpath = "//div[@class='flex flex-col items-center my-2 p-2 border rounded cursor-pointer text-white bg-c11-blue hover:bg-opacity-80']")
    public static WebElement LoadKeyfromFileBtn;
    @FindBy(id = "file")
    public
    WebElement loadkey;


    static WebDriverWait wait;
    public void encryptImage_PDF(String path , WebDriver driver) throws InterruptedException {
        choosefilebtn.sendKeys(path);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(encryptWholeBtn));
        encryptWholeBtn.click();
        Thread.sleep(2000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(viewencryptedBTN));
    }
    public void decryptIMG_PDF(String path ,WebDriver driver , boolean b) throws InterruptedException {
        choosefilebtn.sendKeys(path);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(reEncryptbtn));
        // if boolean b is true then we are doing full decryption and if it's false then we dont ned to select edit button and we are in selction decryption
        if(b == true)
        {
            editBtn2.click();
        }
        Thread.sleep(2000);
        removeallencryptionBTN.click();
        Thread.sleep(3000);
        removeallencryptionBTN.click();
        Thread.sleep(200);
        RemoveConfidencialAlert.click();
        Thread.sleep(1000);
    }

    public void decryptIMG_PDF_LocalKey(String path ,WebDriver driver , boolean b ,String key) throws InterruptedException {
        choosefilebtn.sendKeys(path);
        loadkey.sendKeys(key);
        // if boolean b is true then we are doing full decryption and if it's false then we dont need to select edit button and we are in selction decryption
        if(b == true)
        {
            editBtn2.click();
        }
        Thread.sleep(200);
        removeallencryptionBTN.click();
        Thread.sleep(300);
        removeallencryptionBTN.click();
        Thread.sleep(200);
        RemoveConfidencialAlert.click();
        Thread.sleep(1000);
    }


    public void EncryptImage_Selection(String ImagePAth , WebDriver driver) throws InterruptedException {
        choosefilebtn.sendKeys(ImagePAth);
        Thread.sleep(500);
        // Click and hold the mouse button on the element (e.g., imageElement)
        Actions actions = new Actions(driver);
        actions.clickAndHold(Image).perform();
        // Move the cursor to a specific X and Y offset (adjust as needed)
        int xOffset = 300; // Replace with your desired X offset
        int yOffset = 200; // Replace with your desired Y offset
        actions.moveByOffset(xOffset, yOffset).perform();
        actions.release().perform();
        encryptWholeBtn.click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(viewencryptedBTN));
    }
}

