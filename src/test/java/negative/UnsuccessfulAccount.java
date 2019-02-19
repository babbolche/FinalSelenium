package negative;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class UnsuccessfulAccount {
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

//    @AfterMethod
//    public static void quit() {
//        driver.quit();
//    }

    @Test
    public void negativeTest() {

        WebElement myAccount = driver.findElement(By.cssSelector("li.dropdown span"));
        myAccount.click();

        WebElement register = driver.findElement(By.linkText("Register"));
        register.click();

        WebElement firstName = driver.findElement(By.name("firstname"));
        firstName.click();
        firstName.sendKeys("");

        WebElement lastName = driver.findElement(By.name("lastname"));
        lastName.click();
        lastName.sendKeys("");

        WebElement email = driver.findElement(By.name("email"));
        email.click();
        email.sendKeys("");

        WebElement telephone = driver.findElement(By.name("telephone"));
        telephone.sendKeys("");

        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("");

        WebElement passConfirm = driver.findElement(By.id("input-confirm"));
        passConfirm.sendKeys("");

//        WebElement checkboxSubscribe = driver.findElement(By.name("newsletter"));
//       if (!checkboxSubscribe.isSelected()) {
//           checkboxSubscribe.click();
//        }
//        assertTrue(checkboxSubscribe.isSelected());
//
//        WebElement agreePolicy = driver.findElement(By.name("agree"));
//       if (!agreePolicy.isSelected()) {
//           agreePolicy.click();
//       }
//        assertTrue(agreePolicy.isSelected());

        WebElement submitButton = driver.findElement(By.cssSelector("input.btn.btn-primary"));
        submitButton.click();

        WebElement warning = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"));
        String warningText = warning.getText();
        Assert.assertTrue(warningText.contains("Warning: "), "BUG BUG BUGS BUNNY!");

        WebElement firstNameWarning = driver.findElement(By.cssSelector("input#input-firstname+div.text-danger"));
        String firstNameWarningText = firstNameWarning.getText();
        Assert.assertEquals(firstNameWarningText, "First Name must be between 1 and 32 characters!");


        String lastNameWarning = driver.findElement(By.cssSelector("#input-lastname+div.text-danger")).getText();
        Assert.assertEquals(lastNameWarning, "Last Name must be between 1 and 32 characters!");


        String emailWarning = driver.findElement(By.cssSelector("#input-email+div.text-danger")).getText();
        Assert.assertEquals(emailWarning,"E-Mail Address does not appear to be valid!");

        String telephoneWarning = driver.findElement(By.cssSelector("#input-telephone+div.text-danger")).getText();
        Assert.assertEquals(telephoneWarning, "Telephone must be between 3 and 32 characters!");


        String passwordWarning = driver.findElement(By.cssSelector("#input-password+div.text-danger")).getText();
        Assert.assertEquals(passwordWarning, "Password must be between 4 and 20 characters!");


    }
}


