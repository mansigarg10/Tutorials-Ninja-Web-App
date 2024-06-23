package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

/**
 * This class contains common methods.
 *
 * @author Mansi Garg
 */
@Getter
@Setter
public class AbstractComponents {

    private final WebDriver driver;

    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "ul[class='breadcrumb'] li:nth-child(3) a")
    private WebElement breadCrumb;

    public Map<String, String> getJsonData(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filePath);
        Map<String, String> jsonData = mapper.readValue(file, new TypeReference<Map<String, String>>(){});
        return jsonData;
    }

    public void waitForElementToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(breadCrumb));
    }

}
