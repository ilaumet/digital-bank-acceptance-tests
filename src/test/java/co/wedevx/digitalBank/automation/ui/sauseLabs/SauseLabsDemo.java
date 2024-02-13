package co.wedevx.digitalBank.automation.ui.sauseLabs;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;

public class SauseLabsDemo {
    public static void main(String[] args) throws MalformedURLException {

        //how to use sause labs

        //first get saucelabs username and password
        String username = "oauth-aliyatech86-3db86";
        String accessKey = "7131cbc2-ec34-4969-a88c-e756f02746e8";

        //set up url to the hub which is running on saucelabs VMs
        String url = "https://oauth-aliyatech86-3db86:7131cbc2-ec34-4969-a88c-e756f02746e8@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

        DesiredCapabilities capapbilities = new DesiredCapabilities();
        capapbilities.setCapability("platformName", Platform.MAC);
        capapbilities.setCapability("browserName", "chrome");
        capapbilities.setCapability("browserVersion", "latest");

        WebDriver driver = new RemoteWebDriver(new URL(url), capapbilities);
        driver.get("https://www.amazon.com/");
        WebElement searchBox = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        searchBox.sendKeys("Iphone"+ Keys.ENTER);

        WebElement appleBrand = driver.findElement(By.cssSelector("li[id='p_89/Apple']"));
        System.out.println(appleBrand.getText());



    }
}
