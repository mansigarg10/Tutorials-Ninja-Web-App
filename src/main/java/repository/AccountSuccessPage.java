package repository;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.AbstractComponents;

/**
 * This class contains web elements of Account Success page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class AccountSuccessPage extends AbstractComponents {

    private final WebDriver driver;

    public AccountSuccessPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='row']/div/h1")
    private WebElement successMessage;

}
