package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class HomePage extends PageBase
{
    public HomePage(WebDriver driver) throws AWTException {
        super(driver);
        jse =(JavascriptExecutor) driver;
    }
    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div/div[2]/div[3]/button")
    public static WebElement individual_login;

    @FindBy(xpath = "//*[@id=\"container\"]/div/div[2]/div/div[3]/div/div/div[2]/div[4]/button")
    public static WebElement ORG_login;

    public void individual_login(){

System.out.print(individual_login.isDisplayed());
        individual_login.click();
    }
    public void ORG_login(){
        ORG_login.click();
    }
}
