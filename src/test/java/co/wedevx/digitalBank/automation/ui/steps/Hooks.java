package co.wedevx.digitalBank.automation.ui.steps;

import co.wedevx.digitalBank.automation.ui.utils.DBUtils;
import co.wedevx.digitalBank.automation.ui.utils.Driver;
import io.cucumber.java.*;

import static co.wedevx.digitalBank.automation.ui.utils.Driver.getDriver;

public class Hooks {

    @Before("@Registration")
    public void establishConnectionToDB () {
        DBUtils.establishConnection();
    }

    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage() {
        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
    }

    @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario){
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();

    }
    @After()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }


}
