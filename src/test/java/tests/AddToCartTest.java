package tests;

import repository.CommonElements;
import repository.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resource.Base;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class validates the Add to Cart functionality.
 *
 * @author Mansi Garg
 */
public class AddToCartTest extends Base {

    private WebDriver driver;
    private List<String> productsList;

    @BeforeMethod
    public void launchBrowser() throws IOException {
        driver = initializeBrowser();
        String[] requiredProducts = {"MacBook", "iPhone"};
        productsList = Arrays.asList(requiredProducts);
    }

    @Test
    public void addProductsToCart() throws IOException, InterruptedException {
        goToApplication();
        HomePage page = new HomePage(driver);
        List<WebElement> products = page.productsOnHome();
        List<WebElement> buttons = page.getAddToCart();
        for (int i = 0; i < products.size(); i++) {
            String[] formattedProduct = products.get(i).getText().split(" ");
            for (int j = 0; j < formattedProduct.length; j++) {
                if (productsList.contains(formattedProduct[0])) {
                    buttons.get(i).click();
                }
            }
        }
        Thread.sleep(2000);
        CommonElements elements = new CommonElements(driver);
        elements.getViewCart().click();
        elements.getViewCartSecond().click();
        boolean value = validationOfCartItems();
        Assert.assertTrue(value);
    }

    private boolean validationOfCartItems() {
        boolean value = false;
        List<WebElement> productInCart = driver.findElements(By.xpath(
                "//div[@class='table-responsive']//tbody//td[2]"));
        List<String> cartProducts = productInCart.stream()
                .map(element -> element.getText().split("\\*", 2)[0])
                .map(product -> product.replaceAll("\\s+", "")) // Remove spaces
                .collect(Collectors.toList());
        for (int i = 0; i < productInCart.size(); i++) {
            if (cartProducts.stream().anyMatch(productsList::contains)) {
                value = true;
            }
        }
        return value;
    }

}
