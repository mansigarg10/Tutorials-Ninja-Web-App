package tests;

import repository.CommonElements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import resource.Base;
import util.TutorialsNinjaConstants;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class matches the searched item with the array of keywords & perform validations.
 *
 * @author Mansi Garg
 */
public class SearchTest extends Base {


    @Test(dataProvider = "getData")
    public void searchProducts(HashMap<String, String> input) throws IOException {

        String[] keyword = {"HP", "Sony", "Samsung"};

        // Opens the browser
        WebDriver driver = initializeBrowser();

        goToApplication();

        CommonElements commonElements = new CommonElements(driver);
        commonElements.getSearchBox().sendKeys(input.get("product"));
        commonElements.getSearchIcon().click();

        String searchItem = driver.findElement(By.cssSelector("div[class='caption'] a")).getText();
        String[] mainName = searchItem.split(" ");
        String splittedName = mainName[0];

        List<String> keyList = Arrays.asList(keyword);
        if (keyList.contains(splittedName)) {
            Assert.assertTrue(true);
        }

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonData(
                System.getProperty("user.dir") + TutorialsNinjaConstants.SEARCH_JSON);
        return new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}};
    }

}
