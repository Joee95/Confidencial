package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class LoginPage extends PageBase
{
    public LoginPage(WebDriver driver) throws AWTException {
        super(driver);
    }
    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;
    @FindBy(xpath = "/html/body/div/main/section/div/div/div/form/div[3]/button")
    WebElement continuee;
    @FindBy(id = "error-element-password")
    public WebElement error_message;
    @FindBy(xpath = "//*[@id=\"headlessui-menu-button-1\"]/span")  //el esm 3and top left page
    public WebElement account_menu;
    @FindBy(css = ".false.group.flex.rounded-b-md.items-center.w-full.px-5.py-5.text-c11-darkblue")
    WebElement Logoutbtn;
    public void LOGIN(String Username , String Password){
      send_keys(username,Username); // DOL two methods 2a2dar astkhdm behom l functions.
      password.sendKeys(Password);
      click(continuee);
    }
    public void logout(){
        click(account_menu);
        click(Logoutbtn);
    }
}
