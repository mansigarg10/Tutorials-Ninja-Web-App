package tests;

import repository.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resource.Base;
import util.TutorialsNinjaConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * This class validates the checkout functionality.
 *
 * @author Mansi Garg
 */
public class CheckoutTest extends Base {

    @BeforeMethod
    public void launchBrowser() throws IOException {
        WebDriver driver = initializeBrowser();
    }

    @Test(dataProvider = "getData")
    public void placeOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        goToApplication();
        SearchPage searchPage = new SearchPage(driver);
        CommonElements cm = searchPage.searchItem();
        searchPage.getAddToCart().click();
        ProductDisplayPage productDisplayPage = new ProductDisplayPage(driver);
        productDisplayPage.changeQty();
        productDisplayPage.getAddToCartButton().click();
        cm.goForCheckout();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getEmailBox().sendKeys(input.get("email"));
        loginPage.getPassword().sendKeys(input.get("password"));
        loginPage.getLoginButton().click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        driver.findElement(By.xpath("(//input[@name='payment_address'])[2]")).click();
        checkoutPage.fillDetails();
        String expectedMessage = "Success";
        OrderSuccessPage successPage = new OrderSuccessPage(driver);
        boolean value = successPage.validateMessage(expectedMessage);
        System.out.println(value);
        Assert.assertTrue(value);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        String filePath = System.getProperty("user.dir") + TutorialsNinjaConstants.LOGIN_JSON;
        List<HashMap<String, String>> data = getJsonData(filePath);
        return new Object[][]{{data.get(0)}};
    }

}
