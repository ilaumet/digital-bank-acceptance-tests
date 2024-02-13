package co.wedevx.digitalBank.automation.ui.steps;

import co.wedevx.digitalBank.automation.ui.models.AccountCard;
import co.wedevx.digitalBank.automation.ui.models.BankTransaction;
import co.wedevx.digitalBank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalBank.automation.ui.pages.CheckingPage;
import co.wedevx.digitalBank.automation.ui.pages.LoginPage;
import co.wedevx.digitalBank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalBank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckingAccountSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private CheckingPage checkingPage = new CheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);


    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password) {
        loginPage.login(username,password);
    }

    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList)throws InterruptedException {
        checkingPage.createNewChecking(checkingAccountInfoList);
    }

    @Then("the user should see {string}")
    public void the_user_should_see(String expectedConfMessage)throws InterruptedException {
        Thread.sleep(5000);
        expectedConfMessage="Confirmation "+expectedConfMessage + "\n×";
        assertEquals(expectedConfMessage,viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());

    }
    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card( List<AccountCard> accountCardList) {

        Map<String,String> actualResulMap =viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();

        AccountCard expectedResult = accountCardList.get(0);

        assertEquals(expectedResult.getAccountName(),actualResulMap.get("actualAccountName"));
        assertEquals("Account: "+expectedResult.getAccountType(),actualResulMap.get("actualAccountType"));
        assertEquals("Ownership: "+expectedResult.getOwnership(),actualResulMap.get("actualOwnership"));
        assertEquals("Interest Rate: "+expectedResult.getInterestRate(),actualResulMap.get("actualIntRate"));

        String expectedBalance = String.format(Locale.US,"%.2f",expectedResult.getBalance());
        assertEquals("Balance: $"+expectedBalance,actualResulMap.get("actualBalance"));

    }

    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<BankTransaction> expectedTransactions) throws InterruptedException {
     Map<String,String> actualResulMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();
        BankTransaction expectedTransaction = expectedTransactions.get(0);

        assertEquals(expectedTransaction.getCategory(),actualResulMap.get("actualCategory"),"transaction category mismatch");
        assertEquals(expectedTransaction.getAmount(),Double.parseDouble(actualResulMap.get("actualAmount")),"transaction amount mismatch");
        assertEquals(expectedTransaction.getBalance(),Double.parseDouble(actualResulMap.get("actualBalance")),"transaction balance mismatch");




    }

}
