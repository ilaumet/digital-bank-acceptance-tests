package co.wedevx.digitalBank.automation.ui.pages;

import co.wedevx.digitalBank.automation.ui.models.AccountCard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends BaseMenuPage{

    public ViewCheckingAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="new-account-conf-alert")
    private WebElement newAccountConfAlertDiv;

    @FindBy(xpath="//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;

    @FindBy(xpath="//table[@id='transactionTable']/tbody/tr")
    private WebElement firstRowOfTransactons;

    public Map<String, String> getNewlyAddedCheckingAccountTransactionInfoMap() {

        List<WebElement> firstRowColoumns = firstRowOfTransactons.findElements(By.xpath("td"));

        Map<String,String> actualResulMap = new HashMap<>();
        actualResulMap.put("actualCategory", firstRowColoumns.get(1).getText());
        actualResulMap.put("actualDescription", firstRowColoumns.get(2).getText());
        actualResulMap.put("actualAmount", firstRowColoumns.get(3).getText().replace("$", ""));
        actualResulMap.put("actualBalance", firstRowColoumns.get(4).getText().replace("$", ""));

        return actualResulMap;
    }

    public Map<String, String> getNewlyAddedCheckingAccountInfoMap(){

        WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size()-1);

        String actualResult = lastAccountCard.getText();

        Map <String,String> actualResultMap = new HashMap<>();
        actualResultMap.put("actualAccountName",actualResult.substring(0,actualResult.indexOf("\n")).trim());
        actualResultMap.put("actualAccountType",actualResult.substring(actualResult.indexOf("Account:"),actualResult.indexOf("Ownership:")).trim());
        actualResultMap.put("actualOwnership",actualResult.substring(actualResult.indexOf("Ownership:"),actualResult.indexOf("Account Number:")).trim());
        actualResultMap.put("actualAccNum",actualResult.substring(actualResult.indexOf("Account Number:"),actualResult.indexOf("Interest Rate:")).trim());
        actualResultMap.put("actualIntRate",actualResult.substring(actualResult.indexOf("Interest Rate:"),actualResult.indexOf("Balance:")).trim());
        actualResultMap.put("actualBalance",actualResult.substring(actualResult.indexOf("Balance:")).trim());

        return actualResultMap;
    }

//    AccountCard expectedResult = accountCardList.get(0);

    public String getActualCreateAccountConfirmationMessage(){
        return newAccountConfAlertDiv.getText();
    }


}
