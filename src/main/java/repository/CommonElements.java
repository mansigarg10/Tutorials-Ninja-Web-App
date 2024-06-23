package repository;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.AbstractComponents;

/**
 * This class contains the common web elements.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class CommonElements extends AbstractComponents {

    private final WebDriver driver;

    public CommonElements(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[id='search'] input")
    private WebElement searchBox;

    @FindBy(css = "span[class='input-group-btn'] button")
    private WebElement searchIcon;

    @FindBy(css = "div[id='cart'] span[id='cart-total']")
    private WebElement viewCart;

    @FindBy(xpath = "//p[@class='text-right']//a[1]")
    private WebElement viewCartSecond;

    @FindBy(css = "p[class='text-right'] a:last-child")
    private WebElement checkoutFromCart;

    public void goForCheckout() {
        getViewCart().click();
        getCheckoutFromCart().click();
    }

}
