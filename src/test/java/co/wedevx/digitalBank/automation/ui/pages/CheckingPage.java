package co.wedevx.digitalBank.automation.ui.pages;

import co.wedevx.digitalBank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalBank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

import static co.wedevx.digitalBank.automation.ui.utils.Driver.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingPage extends BaseMenuPage{
    public CheckingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="Standard Checking")
    private WebElement standardCheckingAccountTypeRadioButton;
    @FindBy(id="Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id="Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id="Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id="name")
    private WebElement accountNameTxt;

    @FindBy(id="openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id="newCheckingSubmit")
    private WebElement submitBtn;



    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) throws InterruptedException {
        Thread.sleep(5000);
        NewCheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);
        Thread.sleep(5000);

        //the user clicks on the checking button
        checkingMenu.click();
        Thread.sleep(5000);

        //the user clicks in the new checking button
        newCheckingBtn.click();
        Thread.sleep(5000);

        assertEquals(ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"),getDriver().getCurrentUrl(),"new checking btn didnt take to "+ConfigReader.getPropertiesValue("digitalbank.createnewcheckingurl"));

        //the user selects the account type
        if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")){
            standardCheckingAccountTypeRadioButton.click();
        }
        else if(testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking"))
        {
            interestCheckingAccountTypeRadioButton.click();
        }
        else{
            throw new  NoSuchElementException("invalid checking account type option");
        }
        if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")){
            individualOwnershipTypeRadioButton.click();
        }
        else if(testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")){
            jointOwnershipTypeRadioButton.click();
        }
        else{
            throw new  NoSuchElementException("invalid ownership type option. only supports Standard checking and Individual checking");
        }
        Thread.sleep(5000);

        //the user names the account
        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());

        //the user makes the initial deposit
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));

        //the user click in the submit button
        submitBtn.click();

    }


}
