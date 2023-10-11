package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

public class LoginTest extends TestBase
{
    HomePage homeobject;
    LoginPage loginObject;

    public LoginTest() throws AWTException {
    }

    @Test(priority = 0 , groups = {"test1"})
    public void successful_Login_individual_test() throws InterruptedException, AWTException {
        homeobject = new HomePage(driver);
        homeobject.individual_login();
        loginObject = new LoginPage(driver);
        loginObject.LOGIN("esamer@smartec-group.com" , "Jo@MAha113");
        driver.manage().timeouts().implicitlyWait(15 , TimeUnit.SECONDS);
        System.out.print(loginObject.account_menu.isDisplayed());
        Assert.assertTrue(loginObject.account_menu.isDisplayed());
    }
    @Test(dependsOnMethods = {"successful_Login_individual_test"})
    public void userLogout(){
    loginObject.logout();
    System.out.print("UserLogoutMethod");
    System.out.print(homeobject.ORG_login.isDisplayed());
    Assert.assertTrue(homeobject.ORG_login.isDisplayed());
    }
    @Test(enabled = false)
    public void incorrect_password_username_individualLogin(){
        homeobject.individual_login();
        loginObject.LOGIN("esamer@smartec-group.com" , "Jo@MAha1133");
        driver.manage().timeouts().implicitlyWait(120 , TimeUnit.SECONDS);
        System.out.print(loginObject.error_message.isDisplayed());
        Assert.assertTrue(loginObject.error_message.isDisplayed());
}
}
