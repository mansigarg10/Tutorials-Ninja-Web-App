package repository;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.AbstractComponents;

/**
 * This class contains web elements of Order Success page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class OrderSuccessPage extends AbstractComponents {

    private final WebDriver driver;

    public OrderSuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
    private WebElement successBreadCrumb;

    public boolean validateMessage(String expectedMessage) {
        String actualMessage = successBreadCrumb.getText();
        System.out.println(actualMessage);
        return actualMessage.equalsIgnoreCase(expectedMessage);
    }

}
