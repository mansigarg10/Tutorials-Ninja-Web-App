package tests;

import repository.AccountSuccessPage;
import repository.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import resource.Base;

import java.io.IOException;

/**
 * This class validates the Register functionality.
 *
 * @author Mansi Garg
 */
public class RegisterTest extends Base {

    @Test()
    public void register() throws IOException {
        WebDriver driver = initializeBrowser();
        RegisterPage registerPage = new RegisterPage(driver);
        goToApplication();
        AccountSuccessPage accountSuccessPage = registerPage.registerToApplication();
        accountSuccessPage.waitForElementToBeVisible();
        Assert.assertEquals(accountSuccessPage.getSuccessMessage().getText(),
                "Your Account Has Been Created!");
    }

}
