package resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import util.TutorialsNinjaConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * This class initializes and closes the browser after test execution is completed.
 * It also includes methods for taking screenshots and reading JSON data.
 *
 * @author Mansi Garg
 */
public class Base {

    public WebDriver driver;

    String filePath = System.getProperty("user.dir") + TutorialsNinjaConstants.DATA_PROPERTIES;

    Properties prop = new Properties();

    public WebDriver initializeBrowser() throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    public void goToApplication() throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        prop.load(fis);
        driver.get(prop.getProperty("URL"));
    }

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), (Charset) null);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")
                + "//test-output//Screenshots//" + TestCaseName + ".png");
        // String destination  = System.getProperty("user.dir") + "//Screenshots//" + TestCaseName+ ".png";
        // System.out.println(destination);
        FileUtils.copyFile(src, file);
        return System.getProperty("user.dir") + "//Screenshots//" + TestCaseName + ".png";
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.close();
    }

}
