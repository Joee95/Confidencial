package Tests;

import Data.LoadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilites.Helper;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;


public class TestBase {
    public static WebDriver driver;
    protected static Actions actions;
    public static String downloadPath = System.getProperty("user.dir") + "\\Downloads\\EncryptedFile";
    public static String URLprod = LoadProperties.testData.getProperty("URLprod");
    public String URLQA = LoadProperties.testData.getProperty("URLQA");

    public TestBase() throws AWTException {
    }

/*    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        return options;
    }*/

    @BeforeSuite
    // @Parameters({"browser"})
    public void SetUpEnvironment() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--force-device-scale-factor=1");
        driver = new ChromeDriver(options);
        driver.navigate().to(URLQA);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
        driver.manage().window().maximize();
    }

//    @AfterSuite
//    public void CleanUp(){
//        driver.quit();
//    }

    //take screenshot if the test case failed
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.print("Failure and taking screenshot");
            System.out.print(result.getName());
            Helper.takescreenshot(driver, result.getName());
        }

    }

}
