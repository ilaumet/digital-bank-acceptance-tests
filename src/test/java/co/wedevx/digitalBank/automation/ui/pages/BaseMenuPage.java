package co.wedevx.digitalBank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends BasePage {

    public BaseMenuPage(WebDriver driver){
        super(driver);
    }
    @FindBy(id="checking-menu")
    protected WebElement checkingMenu;

    @FindBy(id="new-checking-menu-item")
    protected WebElement newCheckingBtn;

    @FindBy(id="view-checking-menu-item")
    protected WebElement viewCheckingBtn;

    @FindBy(id="home-menu-item")
    protected WebElement homeButton;


    public void goToHomePage(){
        homeButton.click();
    }

}
