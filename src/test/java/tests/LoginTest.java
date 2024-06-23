package tests;

import repository.HomePage;
import repository.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resource.Base;
import util.TutorialsNinjaConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the login functionality.
 *
 * @author Mansi Garg
 */
public class LoginTest extends Base {

    @Test(dataProvider = "getData")
    public void getLoggedIn(HashMap<String, String> input) throws IOException {
        WebDriver driver = initializeBrowser();
        goToApplication();
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = homePage.goToLoginPage();
        loginPage.getEmailBox().sendKeys(input.get("email"));
        loginPage.getPassword().sendKeys(input.get("password"));
        loginPage.getLoginButton().click();
        WebElement accountBreadcrumb = driver.findElement(By.cssSelector(
                "ul[class='breadcrumb'] li:nth-child(2) a"));
        Assert.assertTrue(accountBreadcrumb.isDisplayed());
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        String file = System.getProperty("user.dir") + TutorialsNinjaConstants.LOGIN_JSON;
        List<HashMap<String, String>> data = getJsonData(file);
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }

}
