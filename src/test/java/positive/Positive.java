package positive;

import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Positive {

    private static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String chromePath = Paths.get("chromedriver.exe").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(" http://shop.pragmatic.bg/");
    }

    @AfterMethod
    public static void quit() {
        driver.quit();
    }

    @Test
    public void positiveTest() {

        WebElement myAccount = driver.findElement(By.cssSelector("li.dropdown span"));
        myAccount.click();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();

        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.click();
        firstName.sendKeys("Marina");

        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.click();
        lastName.sendKeys("Radeva");

        WebElement email = driver.findElement(By.name("email"));
        email.click();
        email.sendKeys("marina_radeva" + RandomString.make(4) + "@yahoo.com");

        WebElement telephone = driver.findElement(By.name("telephone"));
        telephone.sendKeys("0887654321");

        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("123456");

        WebElement passConfirm = driver.findElement(By.id("input-confirm"));
        passConfirm.sendKeys("123456");

        WebElement checkboxSubscribe = driver.findElement(By.name("newsletter"));
        if (!checkboxSubscribe.isSelected()) {
            checkboxSubscribe.click();
        }
        assertTrue(checkboxSubscribe.isSelected());

        WebElement agreePolicy = driver.findElement(By.name("agree"));
        if (!agreePolicy.isSelected()) {
            agreePolicy.click();
        }
        assertTrue(agreePolicy.isSelected());

        WebElement submitButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        submitButton.click();

        WebElement success = driver.findElement(By.id("content"));
        String successText = success.getText();
        Assert.assertTrue(successText.contains("Your new account has been successfully created!"), "BUG BUG BUGS BUNNY!");

    }
}
