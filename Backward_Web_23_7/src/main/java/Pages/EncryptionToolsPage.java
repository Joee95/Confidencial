package Pages;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EncryptionToolsPage extends PageBase {

    public EncryptionToolsPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    Robot robot = new Robot();
    public static JavascriptExecutor js = (JavascriptExecutor) driver;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[2]/textarea")
    public
    WebElement Textarea;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div[4]/button")
    WebElement encrypt_btn;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[2]/div/div/div/div[3]/div/button")
    public WebElement decrypt_btn;

    @FindBy(css = ".h-64.border-2.border-black.w-full.p-4")
    public static WebElement encryption_result;

    @FindBy(xpath = "//*[@id=\"headlessui-dialog-panel-3\"]")
    public static WebElement load_key;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/button[3]/div")
    WebElement clear_results_btn;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[2]/div/svg/path")
    WebElement exist_btn;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[1]/div[1]/div[2]/div[2]/div/div[2]")
    public WebElement PDF_protector_btn;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[1]/div[1]/div[2]/div[1]/div")
    public WebElement encryption_tools_btn;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/button[2]/div")
    WebElement COPY_BTN;
    @FindBy(css = ".break-words.opacity-50")
    public static List<WebElement> List_of_recipients;
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div/div[1]/div[2]/div/div[1]/span[2]")
    WebElement ChooseFileBtn;
    @FindBy(css = ".flex-1.h-64.w-full.flex.flex-col.my-auto.items-center.justify-center.p-4.border-2.border-gray-400.bg-slate-100.rounded")
    public
    WebElement encryption_result_files;

    @FindBy(xpath = "//input[@type='file' and @id='file']")
    WebElement Uploadbtn;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[2]/div[2]/div/button[1]")
    public static WebElement Save_As_Btn;
    @FindBy(id = "file")
    WebElement textarea_file;

    public void encrypt_text(String text) throws InterruptedException {
        send_keys(Textarea, text);
        Thread.sleep(1200);
        encrypt_btn.click();
        Thread.sleep(2000);
        COPY_BTN.click();
    }

    public static List<String> get_list_of_recipients() {
        System.out.println(List_of_recipients.size());
        List<String> names_recipients = new ArrayList<String>();
        for (WebElement web_Element : List_of_recipients) {
            names_recipients.add(web_Element.getText());
        }
        return names_recipients;
    }

    public void decrypt_text() throws InterruptedException {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        decrypt_btn.click();
        Thread.sleep(1200);
        scroll_to_bottom();
        Thread.sleep(1200);
     /*  int i;
       for(i = 0; i < Text.length(); i++) {
           String s = new StringBuilder().append(Text.charAt(i)).toString();
           Textarea.sendKeys(s);
       }*/
    }

    public void decrypt_text(String encrypted_text) throws InterruptedException {
        Textarea.sendKeys(encrypted_text);
        Thread.sleep(3000);
        decrypt_btn.click();
    }

    public void encryptFiles(String path) throws InterruptedException, AWTException {
        textarea_file.sendKeys(path);
        encrypt_btn.click();
    }

    public void filesDecryption(String path) throws AWTException, InterruptedException {
        textarea_file.sendKeys(path);
        Thread.sleep(500);
        decrypt_btn.click();
    }
}