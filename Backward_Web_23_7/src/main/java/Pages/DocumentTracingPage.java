package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DocumentTracingPage extends PageBase {

    public DocumentTracingPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    public static WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div[1]/div[1]/div[2]/div[5]/div")
    public static WebElement Document_Tracing;

    @FindBy(xpath = "//div[@class='flex flex-row items-center justify-center gap-x-2' and contains (. , 'Search')]")
    public static WebElement SearchBtn;

    @FindBy(xpath = "//input[@class='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md placeholder-gray-400 focus:outline-none focus:ring-c11-blue focus:border-c11-blue sm:text-sm' and @name='name']")
    public static WebElement Document_Name_Field;

    @FindBy(xpath = "//input[@class='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md placeholder-gray-400 focus:outline-none focus:ring-c11-blue focus:border-c11-blue sm:text-sm' and @name='uuid']")
    public static WebElement TracerID_Field;

    @FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]/div/div/div[3]/button[2]/div")
    public static WebElement Search_Result_Btn;

    @FindBy(xpath = "//div[@class='flex flex-col w-full overflow-clip']")
    public static List<WebElement> Request_Names;

    @FindBy(xpath = "//input[@class='appearance-none block w-full px-3 py-2 border border-gray-300 rounded-md placeholder-gray-400 focus:outline-none focus:ring-c11-blue focus:border-c11-blue sm:text-sm pr-8']")
    public static WebElement Date_Range_Field;

    @FindBy(xpath = "//span[@class='rdrStaticRangeLabel' and contains (. , 'This Month')]")
    public static WebElement This_Month_Btn;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div/div/div[2]/div/div[1]/div[2]/div[2]")
    public static WebElement Tracer_ID;


    public static List<String> get_list_of_request_names() {
        System.out.println(Request_Names.size());
        List<String> request_names = new ArrayList<String>();
        for (WebElement web_Element : Request_Names) {
            request_names.add(web_Element.getText());
        }
        return request_names;
    }

    public void Search_Request_By_Document_Name(String Document_Name, WebDriver driver) throws InterruptedException {
        Document_Tracing.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(SearchBtn));
        SearchBtn.click();
        wait.until(ExpectedConditions.visibilityOf(Document_Name_Field));
        Document_Name_Field.sendKeys(Document_Name);
        wait.until(ExpectedConditions.visibilityOf(Search_Result_Btn));
        Search_Result_Btn.click();
    }

    public void Search_Request_By_Tracer_ID(String Tracer_ID, WebDriver driver) throws InterruptedException {
        Document_Tracing.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(SearchBtn));
        SearchBtn.click();
        wait.until(ExpectedConditions.visibilityOf(TracerID_Field));
        TracerID_Field.sendKeys(Tracer_ID);
        wait.until(ExpectedConditions.visibilityOf(Search_Result_Btn));
        Search_Result_Btn.click();
    }

    public void Search_Request_By_Date_Range(WebDriver driver) throws InterruptedException {
        Document_Tracing.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500));
        wait.until(ExpectedConditions.visibilityOf(SearchBtn));
        SearchBtn.click();
        wait.until(ExpectedConditions.visibilityOf(Date_Range_Field));
        Date_Range_Field.click();
        wait.until(ExpectedConditions.visibilityOf(This_Month_Btn));
        This_Month_Btn.click();
        Search_Result_Btn.click();
    }


}
