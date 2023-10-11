package Pages;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.checkerframework.checker.formatter.qual.Format;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PageBase
{
    public static WebDriver driver;
    Robot robot = new Robot();
    public static JavascriptExecutor jse;
    public PageBase(WebDriver driver) throws AWTException {
        PageFactory.initElements(driver ,this);
    }
// 3ashan law ayza a3ml re-factoring ll two function dol.

   public void save_files(String path) throws AWTException, InterruptedException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(path);
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
    public Boolean CompareFiles_Hashfn(String File1, String File2) throws IOException {
        HashCode hc1= Files.hash(new File(File1), Hashing.sha1());
        System.out.print(hc1);
        System.out.print(" this is second hash function ");
        HashCode hc2= Files.hash(new File(File2),Hashing.sha1());
        System.out.print(hc2);
        if (hc2.equals(hc1)) return true;
        else return false;
    }
    public static void click(WebElement btn){
        btn.click();

    }
    protected static void send_keys(WebElement element ,String value){
        element.sendKeys(value);
    }
    protected static void scroll_to_bottom(){
        jse.executeScript("scrollBy(0,2500)");

    }
}
