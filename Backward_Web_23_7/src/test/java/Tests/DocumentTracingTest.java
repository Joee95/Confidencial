package Tests;

import Data.LoadProperties;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static Tests.EncryptionToolsTest.passwordQA;
import static Tests.EncryptionToolsTest.usernameQA;

public class DocumentTracingTest extends TestBase {

    HomePage homeobject;
    LoginPage loginobject;
    PageBase page;
    DocumentTracingPage documentTracingPage;

    static List<String> Request_Names = new ArrayList<String>();

    public static String Document_Name = LoadProperties.testData.getProperty("Document_Name");
    public static String Tracer_ID = LoadProperties.testData.getProperty("Tracer_ID");

    public DocumentTracingTest() throws AWTException {
    }

    @Test(priority = 1)
    public void Search_Document_Name() throws AWTException, InterruptedException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginobject = new LoginPage(driver);
        page = new PageBase(driver);
        documentTracingPage = new DocumentTracingPage(driver);
        loginobject.LOGIN(usernameQA, passwordQA);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        documentTracingPage.Search_Request_By_Document_Name(Document_Name, driver);
        Request_Names = documentTracingPage.get_list_of_request_names();
        Assert.assertFalse(Request_Names.isEmpty());
    }

    @Test(priority = 2, dependsOnMethods = "Search_Document_Name")
    public void Search_Tracer_ID() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        documentTracingPage.Search_Request_By_Tracer_ID(Tracer_ID, driver);
        Assert.assertEquals(documentTracingPage.Tracer_ID.getText(), "d3a22e0b-bbc3-49bf-826d-abad7f5a34ed");
    }

    @Test(priority = 3, dependsOnMethods = "Search_Tracer_ID")
    public void Search_Date_Range() throws AWTException, InterruptedException {
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        documentTracingPage.Search_Request_By_Date_Range(driver);
        Request_Names = documentTracingPage.get_list_of_request_names();
        Assert.assertTrue(Request_Names.get(1).contains("ISTQB_CTFL_Syllabus-v4.0.pdf"));
    }
}
