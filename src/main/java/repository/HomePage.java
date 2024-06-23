package repository;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.AbstractComponents;

import java.util.List;

/**
 * This class contains web elements of Home page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class HomePage extends AbstractComponents {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[title='My Account']")
    private WebElement myAccount;

    @FindBy(css = "ul[class*='dropdown-menu-right'] li:nth-child(1) a")
    private WebElement register;

    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu-right')]//li//a[contains(.,'Login')]")
    private WebElement loginOption;

    @FindBy(xpath = "//div[@class='caption']//a")
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='button-group']//button//span[text()='Add to Cart']")
    private List<WebElement> addToCart;

    public void goToRegisterPage() {
        myAccount.click();
        register.click();
    }

    public LoginPage goToLoginPage() {
        myAccount.click();
        loginOption.click();
        LoginPage loginPage = new LoginPage(driver);
        return loginPage;
    }

    public List<WebElement> productsOnHome() {
        return products;
    }

}
