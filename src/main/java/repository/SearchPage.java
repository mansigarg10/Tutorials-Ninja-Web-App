package repository;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.AbstractComponents;

/**
 * This class contains web elements of Search page.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class SearchPage extends AbstractComponents {

    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Getter
    @FindBy(css = "div[class='button-group'] button:first-child")
    private WebElement addToCart;

    public CommonElements searchItem() {
        CommonElements cm = new CommonElements(driver);
        cm.getSearchBox().sendKeys("hp");
        cm.getSearchIcon().click();
        return cm;
    }

}
